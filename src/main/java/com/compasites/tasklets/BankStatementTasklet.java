package com.compasites.tasklets;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import com.oracle.xmlns.apps.financials.commonmodules.shared.model.erpintegrationservice.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobhan Babu on 06-06-2016.
 */
public class BankStatementTasklet implements Tasklet {

    static Logger LOG = LoggerFactory.getLogger(BankStatementTasklet.class);

    @Autowired
    IntegrationServiceHelper integrationServiceHelper;

    @Value("${bankstatement.inputDir}")
    private String inputDir;

    @Value("${bankstatement.filelookup.dir}")
    private String lookupDir;

    @Value("${bankstatement.inputfile}")
    private String inputfilename;

    @Value("${bankstatement.csvfile}")
    private String convertfile;

    @Value("${bankstatement.zipfile}")
    private String zipFile;

    @Value("${bankstatement.zipfilename}")
    private String zipFileName;

    @Value("${bankstatement.documenttitle}")
    private String documentTitle;

    @Value("${bankstatement.account}")
    private String bankstmtAccount;

    @Value("${bankstatement.job.packagename}")
    private String packageName;

    @Value("${bankstatement.job.definitionname}")
    private String jobDefinitionName;

    @Value("${bsh.bankstmt.mappingfile.location}")
    private String mappingFile;

    @Value("${bankstatement.processedDir}")
    private String processedDir;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        try {
            File dirPath = new File(lookupDir);
            String inputFile = FileUtil.lastModifiedFile(dirPath, Constants.TXT_EXTENSION_FILES);
            if(inputFile == null){
                inputFile = Constants.BANKSTMT_FILENAME;
            }

            //File file = new File(inputDir + inputfilename);
            File file = new File(inputDir + inputFile);
            if (file.exists()) {
                String extConvertFile = inputFile.replaceAll(Constants.TXT_EXTENSION_FILES, Constants.EXTENSION_FILES);

                //to rename it ot to csv
                file.renameTo(new File(inputDir + extConvertFile));

                String[] sourceFiles = {inputDir + extConvertFile};
                String[] destFiles = {extConvertFile};
                FileUtil.createZip(zipFile, sourceFiles, destFiles);

                LOG.debug("bank statement zip file has been created!");

                String moveTime = DateUtil.getDateTime();

                //after completion of operation input file is moved to processed folder
                //FileUtil.moveInputFile(inputDir + convertfile, processedDir, convertfile, moveTime);
                FileUtil.moveInputFile(inputDir + extConvertFile, processedDir, extConvertFile, moveTime);

                submitToFusion(zipFile);

                //after completion of operation zip file moves to processed folder
                FileUtil.moveInputFile(zipFile, processedDir, zipFileName, moveTime);
            }
        }catch (IOException ioe){
            LOG.error("IOException message :" + ioe.getMessage());
            LOG.error("IOException :", ioe);
            throw ioe;
        }
        return RepeatStatus.FINISHED;
    }

    private String submitToFusion(String zipFile) throws EvalError, IOException, ServiceException, InterruptedException{
        //It is to upload file to UCM
        String uploadFileId = integrationServiceHelper.uploadFileToUcm(bankstmtAccount,
                zipFile, zipFileName, documentTitle);
        LOG.debug("Bank statement upload file id : " + uploadFileId);

        List<String> paramList = configureBeanshell("setSubmitJobParams(list)", 1, uploadFileId);
        LOG.debug("Bank statement UCM submit paramlist : " + paramList);

        //It is to submit file
        long processId = integrationServiceHelper.submitEssJobRequest(packageName,
                jobDefinitionName, paramList);
        LOG.debug("Bank statement UCM submit process Id : " + processId);

        //It is to check the status of job
        String status = integrationServiceHelper.checkJobStatus(processId);
        LOG.debug("Bank statement UCM submit status : " + status);
        if(status.equalsIgnoreCase("error")){
            LOG.error("Bank Statement job submit status is error with process Id : " + processId);
        }
        return status;
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
}
