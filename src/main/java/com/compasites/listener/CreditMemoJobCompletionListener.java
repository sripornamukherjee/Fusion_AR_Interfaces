package com.compasites.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.compasites.constants.Constants;
import com.compasites.helper.CreditMemoSingleton;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.helper.InvoiceSingleton;
import com.compasites.helper.ReportRunHelper;
import com.compasites.pojo.CreditMemo;
import com.compasites.pojo.Customer;
import com.compasites.pojo.Invoice;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import com.compasites.validations.CommonValidation;
import com.oracle.xmlns.apps.financials.commonmodules.shared.model.erpintegrationservice.ServiceException;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Created by Sobhan Babu on 02-06-2016.
 */
public class CreditMemoJobCompletionListener extends JobExecutionListenerSupport {

    static Logger LOG = LoggerFactory.getLogger(CreditMemoJobCompletionListener.class);

    @Autowired
    IntegrationServiceHelper integrationServiceHelper;
    
    @Autowired
    private ReportRunHelper reportRunHelper;

    @Value("${creditmemo.inputDir}")
    private String inputDir;

    @Value("${creditmemo.inputfile}")
    private String inputFile;

    @Value("${creditmemo.processedDir}")
    private String processedDir;

    @Value("${invoice.output.rainterface}")
    private String interfaceFile;

    @Value("${invoice.output.radistribution}")
    private String distribInterfaceFile;

    @Value("${creditmemo.output.folder}")
    private String outputFolder;

    @Value("${creditmemo.output.dir}")
    private String outputDir;

    @Value("${bsh.creditmemo.mappingfile.location}")
    private String mappingFile;

    @Value("${creditmemo.output.filepath}")
    private String invoiceZipFile;

    @Value("${creditmemo.zipfilename}")
    private String zipFileName;

    @Value("${creditmemo.account}")
    private String invoiceAccount;

    @Value("${creditmemo.documenttitle}")
    private String documentTitle;

    @Value("${creditmemo.job.packagename}")
    private String jobPackageName;

    @Value("${creditmemo.job.definitionname}")
    private String jobDefinitionName;

    @Value("${creditmemo.application.packagename}")
    private String appJobPackageName;

    @Value("${creditmemo.application.definitionname}")
    private String appJobDefinitionName;

    @Value("${creditmemo.errorfolder}")
    private String errorFolder;

    @Value("${creditmemo.errorfile}")
    private String filename;
    
    @Value("${creditmemo.renamefile}")
    private String renameFile;

    @Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution){
        File invoiceInputFile = new File(inputDir + CreditMemo.creditmemoFile);
        //File invoiceInputFile = new File(inputDir + inputFile);
        //String line = null;
        //String previousLine = null;
        //FileReader fileReader = null;
        //BufferedReader bufferedReader = null;

        try {
            if (invoiceInputFile.exists() && invoiceInputFile.length() > 0) {
                //to delete the files in output directory
                FileUtil.deleteOutputFiles(outputDir);
                Customer.errorTime = null;

                //remove old instance
                CreditMemoSingleton singleton = CreditMemoSingleton.getInstance();
                singleton.removeInstance();

                //creating new instance
                singleton = CreditMemoSingleton.getInstance();
                singleton.setErrorFileDateTime(DateUtil.getDateTime());
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

        //File invoiceInputFile = new File(inputDir + inputFile);
        File invoiceInputFile = new File(inputDir + CreditMemo.creditmemoFile);
        File invoiceInterfaceFile = new File(attchPath(interfaceFile));

        if (jobExecution.getStatus() == BatchStatus.COMPLETED && invoiceInputFile.exists() && invoiceInterfaceFile.exists()) {
            try {
                LOG.debug("!!! Credit Memo JOB FINISHED! Time to verify the results");

                writeToFiles();
                
                File distFile = new File(attchPath(distribInterfaceFile));
                distFile.createNewFile();

                String zipFile = invoiceZipFile;
                String[] sourceFiles = {attchPath(interfaceFile), attchPath(distribInterfaceFile)};

                String[] destFiles = {interfaceFile, distribInterfaceFile};

                FileUtil.createZip(zipFile, sourceFiles, destFiles);

                LOG.debug("Credit memo Zip file has been created!");

                String moveTime = DateUtil.getDateTime();

                FileUtil.moveInputFile(inputDir + CreditMemo.creditmemoFile, processedDir, CreditMemo.creditmemoFile, moveTime);

                if (invoiceInterfaceFile.length() > 0) {
                    //method to push data into fusion application
                    submitToFusion(zipFile);
                }

                //to delete the generated output files
                FileUtil.deleteOutputFiles(outputDir);

                //after completion of operation zip file moves to processed folder
                FileUtil.moveInputFile(zipFile, processedDir, zipFileName, moveTime);

            } catch (IOException ioe) {
                LOG.error("IOException message :" + ioe.getMessage());
                LOG.error("IOException :", ioe);
            } catch (EvalError evlError) {
                LOG.error("evlError message : " + evlError.getMessage());
                LOG.error("evlError : ", evlError);
            } catch(ServiceException se) {
                throw new RuntimeException("ServiceException occured.");
            } catch(InterruptedException ie){
                throw new RuntimeException("InterruptedException occured.");
            }
        }
    }
    
    private void writeToFiles() throws IOException{
        CreditMemoSingleton singleton = CreditMemoSingleton.getInstance();
        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        FileWriter writer = new FileWriter(outputFolder + interfaceFile, true);
        BufferedWriter bw = new BufferedWriter(writer);

        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        
        try{
            boolean valid = true;
            ArrayList<CreditMemo> creditMemoList = singleton.getValidList();
            if(creditMemoList == null) {
                creditMemoList = singleton.getErrorList();
                valid = false;
            }
            if (creditMemoList != null) {
                if (valid) {
                	CreditMemo taxLine = null;
                	for (CreditMemo cm : creditMemoList) {
                	BigDecimal headerAmtSum = new BigDecimal(df.format(cm.getHeaderAmt()));
                    BigDecimal grossTotalAmt = new BigDecimal(df.format(new BigDecimal(cm.getGrossTotalAmt())));
                    BigDecimal gst = new BigDecimal(df.format(new BigDecimal(cm.getGstAmount())));
                    
                    BigDecimal allocatedRev = new BigDecimal(cm.getAllocatedRevAmt());
	                if (gst.add(headerAmtSum).compareTo(grossTotalAmt) != 0){
	                    cm.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
	                    errorFileBw.write(cm.getErrorLine());
                    } else {

                    	if(!cm.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) {
                        	if(allocatedRev.signum() == 1)
                        		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
                        		
                        	BigDecimal newAmt = allocatedRev.add(new BigDecimal(cm.getDiscountAmt()));
                        	cm.setTransactionLineAmt(newAmt.toString());
                        	cm.setUnitSellingPrice(newAmt.toString());
                        }
                        if(!cm.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) {
                        	if(allocatedRev.signum() == 1)
                        		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
                        		
                        	BigDecimal newAmt1 = allocatedRev.add(new BigDecimal(cm.getForfeitedAmt()));
                        	cm.setTransactionLineAmt(newAmt1.toString());
                        	cm.setUnitSellingPrice(newAmt1.toString());
                        }
                        if(!cm.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) {
                        	if(allocatedRev.signum() == 1)
                        		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
                        		
                        	BigDecimal newAmt2 = allocatedRev.add(new BigDecimal(cm.getCouponAmt()));
                        	cm.setTransactionLineAmt(newAmt2.toString());
                        	cm.setUnitSellingPrice(newAmt2.toString());
                        }
                        
                        if(headerAmtSum.signum() == 1) {
                        	if(cm.getMemoLineName().equals(Constants.SFC_MEMOLINE))
                        		cm.setLineAmtIncludesTaxFlag("Y");
                        	else
                        		cm.setLineAmtIncludesTaxFlag("N");
                        }else
                        	cm.setLineAmtIncludesTaxFlag("");
                       
                        bw.write(cm.getContent());
                        if(!cm.getMemoLineName().equals(Constants.WDA_MEMOLINE) && 
                        		!cm.getMemoLineName().equals(Constants.SFC_MEMOLINE) &&
                        		allocatedRev.signum() > 0)
                        	taxLine = cm;	
                        }
                    }
                if(taxLine != null) {
                	boolean isNotEmptyVoucherRef = CommonValidation.stringNotEmpty(taxLine.getVoucherRefNumber());
        			
        			if(isNotEmptyVoucherRef) {
        				BigDecimal s = new BigDecimal(taxLine.getGstAmount()).multiply(new BigDecimal(-1));
        				String refAmount = df.format(s);
        				
        				String invoiceTaxRef = reportRunHelper.getInvoiceTaxLineReference(taxLine.getCustomerId(),
            				taxLine.getVoucherRefNumber(), refAmount);
        				
        				String[] arr = new String[3];
         			     if (invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_CONTEXT) >= 0) {
         			    	 arr[0] = invoiceTaxRef.substring(invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_CONTEXT) + 24, invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_CONTEXT_END));
                         }
                         if (invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1) > 0) {
                        	 arr[1] = invoiceTaxRef.substring(invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1) + 27, invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1_END));
                         }
                         if (invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2) > 0) {
                        	 arr[2] = invoiceTaxRef.substring(invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2) + 27, invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2_END));
                         }
                        if (arr[0] != null && arr[1] != null && arr[2] != null) {	
                        	taxLine.setRefTransactionFlexfieldCntxt(arr[0]);
                        	taxLine.setRefTransactionFlexfieldSegment1(arr[1]);
                        	taxLine.setRefTransactionFlexfieldSegment2(arr[2]);
        				} else {
        					taxLine.setRefTransactionFlexfieldCntxt(Constants.EMTPY);
        					taxLine.setRefTransactionFlexfieldSegment1(Constants.EMTPY);
        					taxLine.setRefTransactionFlexfieldSegment2(Constants.EMTPY);
        					taxLine.setCreditMtdUsedRevenueSchedulingRules(Constants.EMTPY);
        				}
        			}
        			
	        		taxLine.setTransactionLineAmt(taxLine.getGstAmount());
	        		taxLine.setUnitSellingPrice(taxLine.getGstAmount());
	        		taxLine.setTransactionLineDescr("");
	        		LOG.info("Writing tax line");
	        		taxLine.setLinkToTransactionsFlexfieldSegment2(taxLine.getLineTransactionFlexfieldSeg2());
	        		singleton.setLineSegment(taxLine);
	        		
	        		taxLine.setLinkToTransactionsFlexfieldCntxt(taxLine.getLineTransactionFlexfieldContxt());
	        		taxLine.setLinkToTransactionsFlexfieldSegment1(taxLine.getLineTransactionFlexfieldSeg1());
	        		
	        		bw.write(taxLine.getTaxLine());
                }
                	    
            } else {
                    for (CreditMemo invoce : creditMemoList) {
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
        LOG.debug("Credit memo upload file id  : " + uploadFileId);

        List<String> paramList = configureBeanshell("setSubmitJobParams(list)", 1, uploadFileId);
        LOG.debug("Credit memo UCM submit paramlist : " + paramList);

        //It is to submit file
        long processId = integrationServiceHelper.submitEssJobRequest(jobPackageName, jobDefinitionName, paramList);
        LOG.debug("Credit memo UCM submit process id  : " + processId);

        //It is to check the status of job
        String status = integrationServiceHelper.checkJobStatus(processId);
        LOG.debug("Credit memo UCM submit status : " + status);

        if (status.equalsIgnoreCase(Constants.UCM_SUCCESS_VALUE)){
            //It is to submit the data to application
            paramList = configureBeanshell("setApplicationJobParams(list)", 2, uploadFileId);
            LOG.debug("Credit memo submit to application paramlist : " + paramList);

            //It is to submit file
            long procId = integrationServiceHelper.submitEssJobRequest(appJobPackageName, appJobDefinitionName, paramList);
            LOG.debug("Credit memo submit to application process id  : " + procId);

            //It is to check the status of job
            String stats = integrationServiceHelper.checkJobStatus(procId);
            LOG.debug("Credit memo submit to application status : " + stats);
            if (stats.equalsIgnoreCase(Constants.UCM_ERROR_VALUE)){
                LOG.error("Error occured while submitting credit memo job to application. JobPackageName = " +
                        appJobPackageName + " JobDefinitionName = " + appJobDefinitionName);
            }
        }else {
            LOG.error("Error occured while submitting credit memo job to UCM. JobPackageName = " +
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
