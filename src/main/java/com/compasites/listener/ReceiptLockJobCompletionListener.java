package com.compasites.listener;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.helper.InvoiceSingleton;
import com.compasites.helper.ReceiptLockSingleton;
import com.compasites.helper.ReceiptSingleton;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Receipt;
import com.compasites.pojo.ReceiptLock;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import com.oracle.xmlns.apps.financials.commonmodules.shared.model.erpintegrationservice.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sobhan Babu on 20-06-2016.
 */
public class ReceiptLockJobCompletionListener extends JobExecutionListenerSupport {

    static Logger LOG = LoggerFactory.getLogger(ReceiptLockJobCompletionListener.class);

    @Autowired
    IntegrationServiceHelper integrationServiceHelper;

    @Value("${receipt.inputDir}")
    private String inputDir;

    @Value("${receipt.inputfile}")
    private String receiptFile;

    @Value("${receipt.processedDir}")
    private String processedDir;

    @Value("${receipt.output.filepath}")
    private String receiptZipFile;

    @Value("${receipt.output.dir}")
    private String outputDir;

    @Value("${receipt.output.folder}")
    private String outputFolder;

    @Value("${receipt.output.lockboxfile}")
    private String lockboxfile;

    @Value("${receipt.account}")
    private String receiptAccount;

    @Value("${receipt.documenttitle}")
    private String documentTitle;

    @Value("${receipt.zipfilename}")
    private String zipFileName;

    @Value("${bsh.receiptlock.mappingfile.location}")
    private String mappingFile;

    @Value("${receipt.application.packagename}")
    private String appJobPackageName;

    @Value("${receipt.application.definitionname}")
    private String appJobDefinitionName;

    @Value("${receipt.job.packagename}")
    private String receiptJobPackageName;

    @Value("${receipt.job.definitionname}")
    private String receiptJobDefinitionName;

    @Value("${receipt.output.lockboxfile}")
    private String interfaceFile;

    @Value("${receipt.renamefile}")
    private String renameFile;

    @Value("${receipt.errorfolder}")
    private String errorFolder;

    @Value("${receipt.errorfile}")
    private String filename;

    @Override
    public void beforeJob(JobExecution jobExecution){
        File invoiceInputFile = new File(inputDir + ReceiptLock.receiptFile);

        try {
            if (invoiceInputFile.exists() && invoiceInputFile.length() > 0) {
                //to delete the files in output directory
                FileUtil.deleteOutputFiles(outputDir);

                //removing old instance.
                ReceiptLockSingleton singleton = ReceiptLockSingleton.getInstance();
                singleton.removeInstance();

                //creating new instance
                singleton = ReceiptLockSingleton.getInstance();
                singleton.setErrorFileDateTime(DateUtil.getDateTime());
                singleton.setLineCount(1);

                //for unique batch name
                String batchName = Constants.RECEIPT_BATCH_INITIAL + GregorianCalendar.getInstance().getTimeInMillis();
                singleton.setBatchName(batchName);

            }
        }finally {
            /*try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (fileReader != null) {
                    fileReader.close();
                }

            }catch (IOException ioe){
                LOG.error("IOException : ", ioe);
            }*/
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //File receiptInputFile = new File(inputDir + receiptFile);
        File receiptInputFile = new File(inputDir + ReceiptLock.receiptFile);
        File inputFile = new File(attchPath(lockboxfile));

        if(jobExecution.getStatus() == BatchStatus.COMPLETED && receiptInputFile.exists() && inputFile.exists()) {
            try {
                //to write the records into file (error/output)
                writeToFiles();

                LOG.debug("!!!Receipt JOB FINISHED! Time to verify the results");

                String zipFile = receiptZipFile;
                String[] sourceFiles = {attchPath(lockboxfile)};

                String[] destFiles = {lockboxfile};

                FileUtil.createZip(zipFile, sourceFiles, destFiles);

                LOG.debug("Receipt Zip file has been created!");

                String moveTime = DateUtil.getDateTime();

                //after completion of operation input file is moved to processed folder
                //FileUtil.moveInputFile(inputDir + receiptFile, processedDir, receiptFile, moveTime);
                FileUtil.moveInputFile(inputDir + ReceiptLock.receiptFile, processedDir, ReceiptLock.receiptFile, moveTime);

                if (inputFile.length() > 0) {
                    //method to push data into fusion application
                    submitToFusion(zipFile);
                }

                //to delete the generated output files
                FileUtil.deleteOutputFiles(outputDir);

                //after completion of operation zip file moves to processed folder
                FileUtil.moveInputFile(zipFile, processedDir, zipFileName, moveTime);

            } catch (IOException ioe) {
                LOG.error("IOException :" + ioe);
            } catch (EvalError evlError) {
                LOG.error("evlError : " + evlError.getMessage());
            } catch (ServiceException se){
                throw new RuntimeException("ServiceException occured");
            } catch(InterruptedException ie){
                throw new RuntimeException("InterruptedException occured.");
            }
        }
    }

    private void writeToFiles() throws IOException{
        ReceiptLockSingleton singleton = ReceiptLockSingleton.getInstance();
        String fileNameParts[] = ReceiptLock.receiptFile.split("\\.");
        String fileName = errorFolder + ReceiptLock.receiptFile.substring(0, ReceiptLock.receiptFile.indexOf('.')) + "_" + singleton.getErrorFileDateTime() + Constants.CSV;
        
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        File uploadFile = new File(outputFolder + interfaceFile);
        FileWriter writer = new FileWriter(uploadFile, true);
        BufferedWriter bw = new BufferedWriter(writer);
        ReceiptLock receipt = null;
        boolean fileNotEmpty = false;
        try{
            boolean valid = true;
            ArrayList<ReceiptLock> invoiceList = singleton.getValidList();
            if(invoiceList == null) {
                invoiceList = singleton.getErrorList();
                valid = false;
            }
            if (invoiceList != null) {
                if (valid) {
                    for (ReceiptLock recipt : invoiceList) {
                        if (uploadFile.length() == 0) {
                            bw.write(recipt.getTransmissionHeader());
                            bw.write(recipt.getServiceHeader());
                            bw.write(recipt.getLockBoxHeader());
                            bw.write(recipt.getBatchHeader());
                            singleton.setCount(1);
                        }
                        recipt.setPaymentItemNumber(String.valueOf(singleton.getCount()));
                        bw.write(recipt.getPayment());
                        singleton.incrementCount();
                        receipt = recipt;
                        fileNotEmpty = true;
                        break;
                    }
                } else {
                    for (ReceiptLock recipt : invoiceList) {
                    	LOG.info("Writing receipt: "+recipt.getReceiptNumber()+" to error file");
                        errorFileBw.write(recipt.getLineData());
                        receipt = recipt;
                    }
                }

                if (uploadFile.length() > 0 || fileNotEmpty) {
                	
                	String trailerLockboxNum = (!(singleton.getTrailerLockboxNum().equalsIgnoreCase(Constants.EMTPY)) && (singleton.getTrailerLockboxNum() != null) ? 
                			singleton.getTrailerLockboxNum() : Constants.EMTPY) ;
                	
                	String trailerReceiptMethod = (!(singleton.getTrailerReceiptMethod().equalsIgnoreCase(Constants.EMTPY)) && (singleton.getTrailerReceiptMethod() != null) ? 
                			singleton.getTrailerReceiptMethod() : Constants.EMTPY) ;
                	
                	String trailerBankOrigNum = (!(singleton.getTrailerBankOrigNum().equalsIgnoreCase(Constants.EMTPY)) && (singleton.getTrailerBankOrigNum() != null) ? 
                			singleton.getTrailerBankOrigNum() : Constants.EMTPY);
                	
                	String trailerDestBankAc = (!(singleton.getTrailerDestBankAc().equalsIgnoreCase(Constants.EMTPY)) && (singleton.getTrailerDestBankAc() != null) ? 
                			singleton.getTrailerDestBankAc() : Constants.EMTPY) ;
                	
                	String trailerDate = (!(singleton.getTrailerDate().equalsIgnoreCase(Constants.EMTPY)) && (singleton.getTrailerDate() != null) ? 
                			singleton.getTrailerDate() : Constants.EMTPY) ;
                	
                	
                	receipt.setLockBoxNumber(trailerLockboxNum);
                	
                	receipt.setReceiptMethod(trailerReceiptMethod);
                	
                	receipt.setBankOrigNumber(trailerBankOrigNum);
                	
                	receipt.setDestBankAccount(trailerDestBankAc);
                	
                	receipt.setLockBoxHdrBankOriginationNumber(trailerBankOrigNum);
                	receipt.setBatchHdrDepositDt(trailerDate);
                	receipt.setLockBoxHdrDepositDt(trailerDate);
                	receipt.setDepositDt(trailerDate);
                	
                    bw.write(receipt.getBatchTrailer());
                    bw.write(receipt.getLockBoxTrailer());
                    bw.write(receipt.getTransmissionTrailer());
                }
            }

            singleton.removeInstance();
        }catch (Exception e){
            LOG.error("Exception message  : " + e.getMessage());
            LOG.error("Exception : ", e);
        }finally {
            bw.flush();
            bw.close();

            erroFileWriter.flush();
            errorFileBw.close();

            //to delete the error file if it is empty
            FileUtil.deleteFile(fileName);
        }
    }

    private void submitToFusion(String zipFile) throws EvalError, IOException, ServiceException, InterruptedException {
        //It is to upload file to UCM

        String uploadFileId = integrationServiceHelper.uploadFileToUcm(receiptAccount,
                zipFile, zipFileName, documentTitle);
        LOG.info("Receipt UCM UploadFileId  : " + uploadFileId);

        List<String> paramList = configureBeanshell("setSubmitJobParams(list)", 1, uploadFileId);
        LOG.info("Receipt loading interface param list : " + paramList);

        //It is to submit file
        long processId = integrationServiceHelper.submitEssJobRequest(receiptJobPackageName,
                receiptJobDefinitionName, paramList);
        LOG.info("Receipt interface load process id : " + processId);

        //It is to check the status of job
        String status = integrationServiceHelper.checkJobStatus(processId);
        LOG.info("Receipt interface load status : " + status);

        if (status.equalsIgnoreCase(Constants.UCM_SUCCESS_VALUE)){
            //It is to submit the data to application
            paramList = configureBeanshell("setApplicationJobParams(list)", 2, String.valueOf(processId));
            LOG.debug("Receipt submit to application paramlist : " + paramList);

            //It is to submit file
            long procId = integrationServiceHelper.submitEssJobRequest(appJobPackageName, appJobDefinitionName, paramList);
            LOG.debug("Receipt submit to application process id  : " + procId);

            //It is to check the status of job
            String stats = integrationServiceHelper.checkJobStatus(procId);
            LOG.debug("Receipt submit to application status : " + stats);
            if (stats.equalsIgnoreCase(Constants.UCM_ERROR_VALUE)){
                LOG.error("Error occured while submitting receipt job to application. JobPackageName = " +
                        appJobPackageName + " JobDefinitionName = " + appJobDefinitionName);
            }
        }else {
            LOG.error("Error occured while submitting receipt job to UCM. JobPackageName = " +
                    receiptJobPackageName + " JobDefinitionName = " + receiptJobDefinitionName);
        }
    }

    private List configureBeanshell(String methodName, int submitNumber, String parameter) throws IOException, EvalError{
        Interpreter i = new Interpreter(); // Construct an interpreter
        i.source(mappingFile);

        List<String> paramList = new ArrayList<String>();
        i.set("list", paramList);
        i.eval(methodName);
        List<String> list = (List) i.get("list");
        if (submitNumber == 1) {
            list.add(1, parameter);
        }else if(submitNumber == 2){
            list.add(1, parameter);
            list.add(2, "SID" + System.currentTimeMillis());

            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_NEW);
            Date dt;
			try {
				dt = format.parse(ReceiptLock.lockboxAccountingDt);
	            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
	            /*Date currentDt = new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);*/
	            
	            list.add(9, sdf.format(dt));
	            //list.add(9, "2016-12-30");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return list;
    }


    private String attchPath(String fileName){
        return outputFolder + fileName;
    }
}
