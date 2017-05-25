package com.compasites.listener;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.pojo.Customer;
import com.compasites.utils.DateUtil;
import com.oracle.xmlns.apps.financials.commonmodules.shared.model.erpintegrationservice.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.compasites.utils.FileUtil;

/**
 * Created by Sobhan Babu on 21-04-2016.
 */
public class CustomerJobCompletionListener extends JobExecutionListenerSupport {

    static Logger LOG = LoggerFactory.getLogger(CustomerJobCompletionListener.class);

    @Autowired
    IntegrationServiceHelper integrationServiceHelper;

    @Value("${customer.inputDir}")
    private String inputDir;

    @Value("${customer.processedDir}")
    private String processedDir;

    @Value("${output.dir}")
    private String outputDir;

    /*@Value("${customer.inputfile}")
    private String customerFile;*/

    @Value("${customer.output.folder}")
    private String outputFolder;

    @Value("${customer.output.filepath}")
    private String custZipFile;

    @Value("${customer.output.partiesfile}")
    private String partiesFile;

    @Value("${customer.output.partysitesfile}")
    private String partysitesFile;

    @Value("${customer.output.partysiteusesfile}")
    private String prtysiteusesFile;

    @Value("${customer.output.accountfile}")
    private String accountFile;

    @Value("${customer.output.accountsitesfile}")
    private String accountsitesFile;

    @Value("${customer.output.accountsiteusesfile}")
    private String accntSiteUsesFile;

    @Value("${customer.output.locationsfile}")
    private String locationFile;

    @Value("${customer.job.packagename}")
    private String customerJobPackageName;

    @Value("${customer.job.definitionname}")
    private String customerJobDefinitionName;

    @Value("${customer.account}")
    private String customerAccount;

    @Value("${customer.documenttitle}")
    private String documentTitle;

    @Value("${customer.zipfilename}")
    private String zipFileName;

    @Value("${bsh.mappingfile.location}")
    private String mappingFile;

    @Value("${customer.application.packagename}")
    private String appJobPackageName;

    @Value("${customer.application.definitionname}")
    private String appJobDefinitionName;

    @Value("${customer.output.accntcontacts}")
    private String accntCntctsFile;

    @Value("${customer.output.contactpoints}")
    private String cntctsPointsFile;

    @Value("${customer.output.contactroles}")
    private String cntctRolesFile;

    @Value("${customer.output.contacts}")
    private String cntctsFile;

    @Value("${customer.output.partyrelationship}")
    private String prtyRelFile;

    @Value("${customer.output.contactResponsibilities}")
    private String cntctResponsibilitiesFile;

    @Value("${customer.output.profilesintall}")
    private String profileIntallFile;

    @Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution){
        //to delete the files in output directory
        FileUtil.deleteOutputFiles(outputDir);

        Customer.errorTime = null;
        Customer.batchId = null;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //File customerInputFile = new File(inputDir + customerFile);
        File customerInputFile = new File(inputDir + Customer.customerFile);
        File interfaceFile = new File(attchPath(partiesFile));
        if(jobExecution.getStatus() == BatchStatus.COMPLETED && customerInputFile.exists() && interfaceFile.exists()) {
            try {
                LOG.debug("!!!Customer JOB FINISHED! Time to verify the results");

                String zipFile = custZipFile;
                String[] sourceFiles = {attchPath(partiesFile), attchPath(partysitesFile),
                        attchPath(prtysiteusesFile), attchPath(accountFile), attchPath(accountsitesFile),
                        attchPath(accntSiteUsesFile), attchPath(locationFile),
                        attchPath(accntCntctsFile), attchPath(cntctsPointsFile), attchPath(cntctRolesFile),
                        attchPath(cntctsFile),attchPath(prtyRelFile),attchPath(cntctResponsibilitiesFile),
                        attchPath(profileIntallFile)};

                String[] destFiles = {partiesFile, partysitesFile,
                        prtysiteusesFile, accountFile, accountsitesFile,
                        accntSiteUsesFile, locationFile,
                        accntCntctsFile, cntctsPointsFile, cntctRolesFile,
                        cntctsFile,prtyRelFile,cntctResponsibilitiesFile,
                        profileIntallFile};

                FileUtil.createZip(zipFile, sourceFiles, destFiles);

                LOG.debug("Customer zip file has been created!");

                //String moveTime =  String.valueOf(System.currentTimeMillis());
                String moveTime = DateUtil.getDateTime();

                //after completion of operation input file is moved to processed folder
                //FileUtil.moveInputFile(inputDir + customerFile, processedDir, customerFile, moveTime);
                FileUtil.moveInputFile(inputDir + Customer.customerFile, processedDir, Customer.customerFile, moveTime);

                //method to push data into fusion application
                if (interfaceFile.length() > 0) {
                    submitToFusion(zipFile);
                }

                //to delete the generated output files
                FileUtil.deleteOutputFiles(outputDir);

                //after completion of operation zip file moves to processed folder
                FileUtil.moveInputFile(zipFile, processedDir, zipFileName, moveTime);

                LOG.debug("Request submitted to oracle fusion.");
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

    private void submitToFusion(String zipFile) throws EvalError, IOException, ServiceException, InterruptedException {
        //It is to upload file to UCM
        String uploadFileId = integrationServiceHelper.uploadFileToUcm(customerAccount,
                zipFile, zipFileName, documentTitle);
        LOG.info("Customer uploadFileId  : " + uploadFileId);

        List<String> paramList = configureBeanshell("setSubmitJobParams(list)", 1, uploadFileId);
        LOG.info("Customer loading interface param list : " + paramList);

        //It is to submit file
        long processId = integrationServiceHelper.submitEssJobRequest(customerJobPackageName,
                customerJobDefinitionName, paramList);
        LOG.info("Customer stage 1 process id : " + processId);

        //It is to check the status of job
        String status = integrationServiceHelper.checkJobStatus(processId);
        LOG.info("Customer stage 1 status : " + status);

        if (status.equalsIgnoreCase(Constants.UCM_SUCCESS_VALUE)){
            //It is to submit the data to application
            paramList = configureBeanshell("setApplicationJobParams(list)", 3, uploadFileId);
            LOG.debug("Customer submit to application paramlist : " + paramList);

            //It is to submit file
            long procId = integrationServiceHelper.submitEssJobRequest(appJobPackageName, appJobDefinitionName, paramList);
            LOG.debug("Customer submit to application process id  : " + procId);

            //It is to check the status of job
            String stats = integrationServiceHelper.checkJobStatus(procId);
            LOG.debug("Customer submit to application status : " + stats);
            if (stats.equalsIgnoreCase(Constants.UCM_ERROR_VALUE)){
                LOG.error("Error occured while submitting customer job to application. JobPackageName = " +
                        appJobPackageName + " JobDefinitionName = " + appJobDefinitionName);
            }
        }else {
            LOG.error("Error occured while submitting customer job to UCM. JobPackageName = " +
                    customerJobPackageName + " JobDefinitionName = " + customerJobDefinitionName);
        }
    }

    private List configureBeanshell(String methodName, int submitNumber, String uploadFileId) throws IOException, EvalError{
        Interpreter i = new Interpreter(); // Construct an interpreter
        i.source(mappingFile);

        List<String> paramList = new ArrayList<String>();
        if (submitNumber == 3) {
            paramList.add(Customer.batchId);
        }
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
