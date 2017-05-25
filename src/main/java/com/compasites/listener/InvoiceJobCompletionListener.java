package com.compasites.listener;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.constants.InvoiceEnum;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.helper.InvoiceSingleton;
import com.compasites.helper.SingletonInvoice;
import com.compasites.pojo.Invoice;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
public class InvoiceJobCompletionListener extends JobExecutionListenerSupport {

    static Logger LOG = LoggerFactory.getLogger(InvoiceJobCompletionListener.class);

    @Autowired
    IntegrationServiceHelper integrationServiceHelper;

    @Value("${invoice.inputDir}")
    private String inputDir;

    @Value("${invoice.processedDir}")
    private String processedDir;

    @Value("${invoice.output.dir}")
    private String outputDir;

    @Value("${invoice.output.filepath}")
    private String invoiceZipFile;

    @Value("${invoice.inputfile}")
    private String invoiceFile;

    @Value("${invoice.output.rainterface}")
    private String interfaceFile;

    @Value("${invoice.output.folder}")
    private String outputFolder;

    @Value("${invoice.account}")
    private String invoiceAccount;

    @Value("${invoice.documenttitle}")
    private String documentTitle;

    @Value("${invoice.zipfilename}")
    private String zipFileName;

    @Value("${invoice.output.radistribution}")
    private String distribInterfaceFile;

    @Value("${invoice.job.packagename}")
    private String jobPackageName;

    @Value("${invoice.job.definitionname}")
    private String jobDefinitionName;

    @Value("${invoice.application.packagename}")
    private String appJobPackageName;

    @Value("${invoice.application.definitionname}")
    private String appJobDefinitionName;

    @Value("${bsh.invoice.mappingfile.location}")
    private String mappingFile;

    @Value("${invoice.errorfolder}")
    private String errorFolder;

    @Value("${invoice.errorfile}")
    private String filename;

    @Value("${invoice.renamefile}")
    private String renameFile;

    /*@Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution) {
        //File invoiceInputFile = new File(inputDir + invoiceFile);
        //String lastLine = FileUtil.reverseFileReader(inputDir + invoiceFile);
        //System.out.println("last line : " + lastLine);
    }*/

    @Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution){
        File invoiceInputFile = new File(inputDir + Invoice.invoicefile);
        //File invoiceInputFile = new File(inputDir + invoiceFile);
        //String line = null;
        //String previousLine = null;
        //FileReader fileReader = null;
        //BufferedReader bufferedReader = null;

        try {
            if (invoiceInputFile.exists() && invoiceInputFile.length() > 0) {
                //to delete the files in output directory
                FileUtil.deleteOutputFiles(outputDir);

                //remove old instance
                InvoiceSingleton singleton = InvoiceSingleton.getInstance();
                singleton.removeInstance();

                //creating new instance
                singleton = InvoiceSingleton.getInstance();
                singleton.setErrorFileDateTime(DateUtil.getDateTime());
                singleton.setLineCount(1);
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
                LOG.error("IOException message : " + ioe);
                LOG.error("IOException : ", ioe);
            }*/
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //File invoiceInputFile = new File(inputDir + invoiceFile);
        File invoiceInputFile = new File(inputDir + Invoice.invoicefile);
        File invoiceInterfaceFile = new File(attchPath(interfaceFile));

        //ADD condition for checking if error file is absent
        if (jobExecution.getStatus() == BatchStatus.COMPLETED && invoiceInputFile.exists() && invoiceInterfaceFile.exists()) {
            try {
                LOG.debug("!!! Invoice JOB FINISHED! Time to verify the results");

                writeToFiles();

                File distFile = new File(attchPath(distribInterfaceFile));
                distFile.createNewFile();

                String zipFile = invoiceZipFile;
                String[] sourceFiles = {attchPath(interfaceFile), attchPath(distribInterfaceFile)};

                String[] destFiles = {interfaceFile, distribInterfaceFile};

                FileUtil.createZip(zipFile, sourceFiles, destFiles);

                LOG.debug("Invoice Zip file has been created!");

                String moveTime = DateUtil.getDateTime();

                //after completion of operation input file is moved to processed folder
                //FileUtil.moveInputFile(inputDir + invoiceFile, processedDir, invoiceFile, moveTime);
                FileUtil.moveInputFile(inputDir + Invoice.invoicefile, processedDir, Invoice.invoicefile, moveTime);

                if (invoiceInterfaceFile.length() > 0) {
                    //method to push data into fusion application
                    submitToFusion(zipFile);
                }

                //to delete the generated output files
                FileUtil.deleteOutputFiles(outputDir);

                //after completion of operation zip file moves to processed folder
                FileUtil.moveInputFile(zipFile, processedDir, zipFileName, moveTime);

                //SingletonInvoice singleton = SingletonInvoice.getInstance();
                //singleton.removeInstance();

            } catch (IOException ioe) {
                LOG.error("IOException message :" + ioe.getMessage());
                LOG.error("IOException :", ioe);
            } catch (EvalError evlError) {
                LOG.error("evlError message : " + evlError.getMessage());
                LOG.error("evlError : ", evlError);
            } catch(ServiceException se){
                throw new RuntimeException("ServiceException occured.");
            } catch(InterruptedException ie){
                throw new RuntimeException("InterruptedException occured.");
            }
        }
    }

    private void writeToFiles() throws IOException{
        InvoiceSingleton singleton = InvoiceSingleton.getInstance();
        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        FileWriter writer = new FileWriter(outputFolder + interfaceFile, true);
        BufferedWriter bw = new BufferedWriter(writer);

        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        
        try{
            boolean valid = true;
            ArrayList<Invoice> invoiceList = singleton.getValidList();
            if(invoiceList == null) {
                invoiceList = singleton.getErrorList();
                valid = false;
            }
            if (invoiceList != null) {
                if (valid) {
                	boolean additionalTaxLineCheck = false;
                	for (Invoice invoce : invoiceList) {
                		additionalTaxLineCheck = invoce.getMemoLineName().equalsIgnoreCase(Constants.SFC_MEMOLINE) &&
                  				!invoce.getGstAmount().equals("0.00") && invoce.getGrossTotalAmt().equals("0.00");
                		if(additionalTaxLineCheck)
                			break;
                	}
                	Invoice taxLine = null;
                    for (Invoice invoce : invoiceList) {  
                    	  BigDecimal checkHeaderAmt = new BigDecimal(df.format(invoce.getHeaderAmt()));
                          BigDecimal checkGrossTotalAmt = new BigDecimal(df.format(new BigDecimal(invoce.getGrossTotalAmt())));
                          BigDecimal roundingDiff = (checkHeaderAmt.subtract(checkGrossTotalAmt)).abs();
                      
                         if (roundingDiff.compareTo(new BigDecimal("0.10")) == 1){
                        	LOG.info("INVOICE NUMBER: "+invoce.getInvoiceNumber()+" being written to error file");
                            invoce.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
                            errorFileBw.write(invoce.getErrorLine());
                        }else {
                        	BigDecimal allocatedRev = new BigDecimal(invoce.getAllocatedRevAmt());
                        	
                        	//If tax needs to be added as separate line
                        	if(additionalTaxLineCheck /*|| !(invoce.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)&
                        			invoce.getWdaFundedAmt().equalsIgnoreCase(Constants.EMTPY))*/) {
                        		invoce.setTaxClassificationCode("");
                        		
                        	} else {
                        		
                        		// If allocated rev is +ve OR (SFC and WDA are empty)
                            	// Deduct discount amount
                            	if(!invoce.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) {
                            		if(allocatedRev.signum() == -1)
                            			allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
                            		
                            		BigDecimal newAmt = allocatedRev.subtract(new BigDecimal(invoce.getDiscountAmt()));
                            		invoce.setTransactionLineAmt(newAmt.toString());
                            		invoce.setUnitSellingPrice(newAmt.toString());
                            	}
                            	if(!invoce.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) {
                            		if(allocatedRev.signum() == -1)
                            			allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
                            		
                            		BigDecimal newAmt1 = allocatedRev.subtract(new BigDecimal(invoce.getForfeitedAmt()));
                            		invoce.setTransactionLineAmt(newAmt1.toString());
                            		invoce.setUnitSellingPrice(newAmt1.toString());
                            	}
                            	if(!invoce.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) {
                            		if(allocatedRev.signum() == -1)
                            			allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
                            		
                            		BigDecimal newAmt2 = allocatedRev.subtract(new BigDecimal(invoce.getCouponAmt()));
                            		invoce.setTransactionLineAmt(newAmt2.toString());
                            		invoce.setUnitSellingPrice(newAmt2.toString());
                            	}
                            	// if GST is 0 or line is for WDA
                            	//LOG.info(invoce.getTaxClassificationCode());
                            	if(invoce.getGstAmount().equals("0.00") /*|| invoce.getMemoLineName().equalsIgnoreCase(Constants.WDA_MEMOLINE)*/) {
                            		// Condition added for MSM 0 GST invoices - OUTPUT-OZR tax code needed
                            		if(!invoce.getGstPercent().contains("0"))
                            			invoce.setTaxClassificationCode("");
                            	}
                            	
                            	invoce.setLineAmtIncludesTaxFlag("");
                        	}

                        	bw.write(invoce.getContent());
                        
                        	if(additionalTaxLineCheck && !(invoce.getMemoLineName().equalsIgnoreCase(Constants.SFC_MEMOLINE) 
                        			|| invoce.getMemoLineName().equalsIgnoreCase(Constants.WDA_MEMOLINE))) {
    			        		taxLine = invoce;
    			        	}
                        }
                    }
                    if(additionalTaxLineCheck && taxLine != null) {
                    	taxLine.setMemoLineName("Tax memo");
		        		taxLine.setTransactionLineAmt(taxLine.getGstAmount());
		        		taxLine.setUnitSellingPrice(taxLine.getGstAmount());
		        		taxLine.setRevenuedSchedulingRuleStrtDt(taxLine.getBillDate());
		        		taxLine.setTransactionLineDescr("To account for GST");
		        		taxLine.setTaxClassificationCode("");
		        		LOG.info("Writing tax line");
		        		singleton.setLineSegment(taxLine);
		        		bw.write(taxLine.getTaxLine());
                    }
                    
                } else {
                    for (Invoice invoce : invoiceList) {
                    	LOG.info("INVOICE NUMBER: "+invoce.getInvoiceNumber()+" being written to error file");
                        errorFileBw.write(invoce.getErrorLine());
                    }
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
        String uploadFileId = integrationServiceHelper.uploadFileToUcm(invoiceAccount, zipFile, zipFileName, documentTitle);
        LOG.debug("Invoice upload file id  : " + uploadFileId);

        List<String> paramList = configureBeanshell("setSubmitJobParams(list)", 1, uploadFileId);
        LOG.debug("Invoice UCM submit paramlist : " + paramList);

        //It is to submit file
        long processId = integrationServiceHelper.submitEssJobRequest(jobPackageName, jobDefinitionName, paramList);
        LOG.debug("Invoice UCM submit process id  : " + processId);

        //It is to check the status of job
        String status = integrationServiceHelper.checkJobStatus(processId);
        LOG.debug("Invoice UCM submit status : " + status);

        if (status.equalsIgnoreCase(Constants.UCM_SUCCESS_VALUE)){
            //It is to submit the data to application
            paramList = configureBeanshell("setApplicationJobParams(list)", 2, uploadFileId);
            LOG.debug("Invoice submit to application paramlist : " + paramList);

            //It is to submit file
            long procId = integrationServiceHelper.submitEssJobRequest(appJobPackageName, appJobDefinitionName, paramList);
            LOG.debug("Invoice submit to application process id  : " + procId);

            //It is to check the status of job
            String stats = integrationServiceHelper.checkJobStatus(procId);
            LOG.debug("Invoice submit to application status : " + stats);
            if (stats.equalsIgnoreCase(Constants.UCM_ERROR_VALUE)){
                LOG.error("Error occured while submitting invoice job to application. JobPackageName = " +
                        appJobPackageName + " JobDefinitionName = " + appJobDefinitionName);
            }
        }else {
            LOG.error("Error occured while submitting invoice job to UCM. JobPackageName = " +
                    jobPackageName + " JobDefinitionName = " + jobDefinitionName);
        }
    }

    private List configureBeanshell(String methodName, int submitNumber, String uploadFileId) throws IOException, EvalError{
        Interpreter i = new Interpreter(); // Construct an interpreter
        i.source(mappingFile);

        List<String> paramList = new ArrayList<String>();
        i.set("list", paramList);
        i.eval(methodName);
        List<String> list = (List) i.get("list");
        if (submitNumber == 1) {
            list.add(1, uploadFileId);
        }else if(submitNumber == 2){
            Date currentDt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
            list.add(2, sdf.format(currentDt));
        }

        return list;
    }

    private String attchPath(String fileName){
        return outputFolder + fileName;
    }
}
