package com.compasites.listener;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.helper.InvoiceSingleton;
import com.compasites.helper.JournalSingleton;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Journal;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobhan Babu on 07-06-2016.
 */
public class JournalJobCompletionListener extends JobExecutionListenerSupport {

    static Logger LOG = LoggerFactory.getLogger(JournalJobCompletionListener.class);

    @Autowired
    IntegrationServiceHelper integrationServiceHelper;

    @Value("${journal.inputDir}")
    private String inputDir;

    @Value("${journal.inputfile}")
    private String journalFile;

    @Value("${journal.output.dir}")
    private String outputDir;

    @Value("${journal.processedDir}")
    private String processedDir;

    @Value("${journal.output.filepath}")
    private String journalZipFile;

    @Value("${journal.output.glinterface}")
    private String interfaceFile;

    @Value("${journal.output.folder}")
    private String outputFolder;

    @Value("${journal.account}")
    private String journalAccount;

    @Value("${journal.documenttitle}")
    private String documentTitle;

    @Value("${journal.zipfilename}")
    private String zipFileName;

    @Value("${journal.job.packagename}")
    private String jobPackageName;

    @Value("${journal.job.definitionname}")
    private String jobDefinitionName;

    @Value("${journal.application.packagename}")
    private String appJobPackageName;

    @Value("${journal.application.definitionname}")
    private String appJobDefinitionName;

    @Value("${bsh.journal.mappingfile.location}")
    private String mappingFile;

    @Value("${journal.renamefile}")
    private String renameFile;

    @Value("${journal.errorfolder}")
    private String errorFolder;

    @Value("${journal.errorfile}")
    private String filename;

    @Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution){

        File journalInputFile = new File(inputDir + Journal.journalFile);
        //File journalInputFile = new File(inputDir + journalFile);
        //String line = null;
        //String previousLine = null;
        //FileReader fileReader = null;
        //BufferedReader bufferedReader = null;

        try {
            if (journalInputFile.exists() && journalInputFile.length() > 0) {
                //to delete the files in output directory
                FileUtil.deleteOutputFiles(outputDir);
                //fileReader = new FileReader(journalInputFile);
                // Always wrap FileReader in BufferedReader.
                //bufferedReader = new BufferedReader(fileReader);

                /*int i = 0;
                BigDecimal amtSum = new BigDecimal(0);

                while ((line = bufferedReader.readLine()) != null) {
                    i++;

                    if ((previousLine != null)) {
                        String receiptSplit[] = line.split(Constants.CVS_SEPERATOR);
                        if (receiptSplit != null && receiptSplit.length > 4) {
                            amtSum = amtSum.add(new BigDecimal(receiptSplit[19]));
                        }
                    }
                    previousLine = line;
                }

                if (i > 1) {
                    String lastLine[] = previousLine.split(Constants.CVS_SEPERATOR);
                    if (lastLine[3] != null && Integer.parseInt(lastLine[3]) != (i - 2)) {
                        LOG.error("Journal line count does not match to number of line in file.");
                        throw new RuntimeException("Journal line count does not match to number of line in file.");
                    }

                    BigDecimal footerAmount = new BigDecimal(lastLine[2]);
                    if (footerAmount.compareTo(amtSum) != 0) {
                        LOG.error("Journal footer amount does not match to sum of line amount.");
                        throw new RuntimeException("Journal footer amount does not match to sum of line amount.");
                    }

                    //remove old instance
                    JournalSingleton singleton = JournalSingleton.getInstance();
                    singleton.removeInstance();

                    //creating new instance
                    singleton = JournalSingleton.getInstance();
                    singleton.setErrorFileDateTime(DateUtil.getDateTime());
                }*/

                //remove old instance
                JournalSingleton singleton = JournalSingleton.getInstance();
                singleton.removeInstance();

                //creating new instance
                singleton = JournalSingleton.getInstance();
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
        File journalInputFile = new File(inputDir + Journal.journalFile);
        //File journalInputFile = new File(inputDir + journalFile);
        File journalInterfaceFile = new File(attchPath(interfaceFile));

        if (jobExecution.getStatus() == BatchStatus.COMPLETED && journalInputFile.exists() && journalInterfaceFile.exists()) {
            try {
                LOG.debug("!!! Journal JOB FINISHED! Time to verify the results");

                writeToFiles();

                String zipFile = journalZipFile;
                String[] sourceFiles = {attchPath(interfaceFile)};

                String[] destFiles = {interfaceFile};

                FileUtil.createZip(zipFile, sourceFiles, destFiles);

                LOG.debug("Journal zip file has been created!");

                String moveTime = DateUtil.getDateTime();

                //after completion of operation input file is moved to processed folder
                //FileUtil.moveInputFile(inputDir + journalFile, processedDir, journalFile, moveTime);
                FileUtil.moveInputFile(inputDir + Journal.journalFile, processedDir, Journal.journalFile, moveTime);

                if (journalInterfaceFile.length() > 0) {
                    //method to push data into fusion application
                    submitToFusion(zipFile);
                }

                //to delete the generated output files
                FileUtil.deleteOutputFiles(outputDir);

                //after completion of operation zip file moves to processed folder
                FileUtil.moveInputFile(zipFile, processedDir, zipFileName, moveTime);

            } catch (IOException ioe) {
                LOG.error("IOException message :" + ioe);
                LOG.error("IOException :", ioe);
            } catch (EvalError evlError) {
                LOG.error("evlError message : " + evlError.getMessage());
                LOG.error("evlError : ", evlError.getMessage());
            } catch(ServiceException se){
                throw new RuntimeException("ServiceException occured.");
            } catch(InterruptedException ie){
                throw new RuntimeException("InterruptedException occured.");
            }
        }
    }

    private void writeToFiles() throws IOException{
        JournalSingleton singleton = JournalSingleton.getInstance();
        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        FileWriter writer = new FileWriter(outputFolder + interfaceFile, true);
        BufferedWriter bw = new BufferedWriter(writer);

        try{
            boolean valid = true;
            ArrayList<Journal> journalList = singleton.getValidList();
            if(journalList == null) {
                journalList = singleton.getErrorList();
                valid = false;
            }
            if (journalList != null) {
                if (valid) {
                    int count = 0;
                    for (Journal jornal : journalList) {
                        count++;
                        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
                        BigDecimal headerAmtSum = new BigDecimal(0);
                        headerAmtSum = headerAmtSum.add(jornal.getHeaderAmt());
                        /*String gstPercent = jornal.getGstPercent();
                        if (gstPercent.length() > 0 && gstPercent.indexOf(Constants.PERCENT_SIGN) > -1){
                            gstPercent = gstPercent.substring(0, gstPercent.length() - 1);
                            BigDecimal gstAmount = headerAmtSum.multiply(new BigDecimal(gstPercent));
                            gstAmount = gstAmount.divide(new BigDecimal(100));
                            gstAmount = new BigDecimal(df.format(gstAmount));
                            headerAmtSum = headerAmtSum.add(gstAmount);
                        }*/
                        BigDecimal gstAmount = new BigDecimal(jornal.getGstAmount());
                        //gstAmount = gstAmount.divide(new BigDecimal(100));
                        gstAmount = new BigDecimal(df.format(gstAmount));
                        headerAmtSum = headerAmtSum.add(gstAmount);
                        
                        //if (jornal.getHeaderAmt().compareTo(new BigDecimal(jornal.getGrossTotalAmt())) != 0){
                        if (headerAmtSum.compareTo(new BigDecimal(jornal.getGrossTotalAmt())) != 0){
                            jornal.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
                            errorFileBw.write(jornal.getErrorLine());
                        }else {
                            bw.write(jornal.getContentCredit());
                            if (count == journalList.size()){
                                bw.write(jornal.getContentDebit());
                            }
                        }
                    }
                } else {
                    for (Journal jornal : journalList) {
                        errorFileBw.write(jornal.getErrorLine());
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
        String uploadFileId = integrationServiceHelper.uploadFileToUcm(journalAccount, zipFile, zipFileName, documentTitle);
        LOG.debug("Journal upload file id : " + uploadFileId);

        List<String> paramList = configureBeanshell("setSubmitJobParams(list)", 1, uploadFileId);
        LOG.debug("Journal UCM submit paramlist : " + paramList);

        //It is to submit file
        long processId = integrationServiceHelper.submitEssJobRequest(jobPackageName, jobDefinitionName, paramList);
        LOG.debug("Journal UCM submit process id : " + processId);

        //It is to check the status of job
        String status = integrationServiceHelper.checkJobStatus(processId);
        LOG.debug("Journal stage 1 status : " + status);

        if (status.equalsIgnoreCase(Constants.UCM_SUCCESS_VALUE)){
            //It is to submit the data to application
            paramList = configureBeanshell("setApplicationJobParams(list)", 2, uploadFileId);
            LOG.debug("Journal application submit paramlist : " + paramList);

            //It is to submit file
            long procId = integrationServiceHelper.submitEssJobRequest(appJobPackageName, appJobDefinitionName, paramList);
            LOG.info("Journal application submit process id : " + procId);

            //It is to check the status of job
            String stats = integrationServiceHelper.checkJobStatus(procId);
            LOG.info("Journal stage 2 status : " + stats);
            if (stats.equalsIgnoreCase(Constants.UCM_ERROR_VALUE)){
                LOG.error("Error occured while submitting journal job to application. JobPackageName = " +
                        appJobPackageName + " JobDefinitionName = " + appJobDefinitionName);
            }
        }else {
            LOG.error("Error occured while submitting journal job to UCM. JobPackageName = " +
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
        }

        return list;
    }

    private String attchPath(String fileName){
        return outputFolder + fileName;
    }


}
