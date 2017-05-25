package com.compasites.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.compasites.constants.Constants;
import com.compasites.pojo.Invoice;
import com.compasites.utils.FileUtil;
import com.oracle.xmlns.apps.financials.commonmodules.shared.model.erpintegrationservice.*;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */

public class IntegrationServiceHelper {

    static Logger LOG = LoggerFactory.getLogger(IntegrationServiceHelper.class);

    @Value("${oraclefusion.username}")
    private String fusionUsername;

    @Value("${oraclefusion.password}")
    private String fusionPassword;

    @Value("${oraclefusion.endpointaddress}")
    private String fusionEndpointAddress;

    @Value("${oraclefusion.wsdl.endpointaddress}")
    private String fusionEndpointWsdlAddress;


    private ErpIntegrationService erpService;

    @PostConstruct
    public void init(){}

    public String uploadFileToUcm(String account, String fileNameWithPath, String fileName, String docTitle) throws ServiceException,
            MalformedURLException, InterruptedException, IOException{
        String uploadFileId = null;
        int count = 1;
        while(true) {
            try {
                URL url = new URL(fusionEndpointWsdlAddress);
                ErpIntegrationService_Service service = new ErpIntegrationService_Service(url);
                ErpIntegrationService erpService = service.getErpIntegrationServiceSoapHttpPort();

                this.erpService = erpService;

                Map<String, Object> requestContext = ((BindingProvider) erpService).getRequestContext();
                requestContext.put(BindingProvider.USERNAME_PROPERTY, fusionUsername);
                requestContext.put(BindingProvider.PASSWORD_PROPERTY, fusionPassword);
                requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, fusionEndpointAddress);

                String filenamewithpath = fileNameWithPath;
                ObjectFactory of = new ObjectFactory();
                JAXBElement<String> ele = of.createDocumentDetailsContentType(Constants.ZIP);

                JAXBElement<String> docAccount = of.createDocumentDetailsDocumentAccount(account);
                JAXBElement<String> docAuthor = of.createDocumentDetailsDocumentAuthor(fusionUsername);
                JAXBElement<String> securityGrp = of.createDocumentDetailsDocumentSecurityGroup(Constants.DOCUMENT_SECURITY_GROUP);

                byte[] content = FileUtil.getByteArray(filenamewithpath);

                //added new for encoding purpose
                //java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
                //content = encoder.encode(content);


                DocumentDetails doc = new DocumentDetails();
                doc.setContent(content);
                doc.setContentType(ele);
                doc.setFileName(fileName);
                doc.setDocumentAccount(docAccount);
                doc.setDocumentAuthor(docAuthor);
                doc.setDocumentSecurityGroup(securityGrp);
                doc.setDocumentTitle(docTitle);
                uploadFileId = erpService.uploadFileToUcm(doc);

                Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                break;
            } catch (InterruptedException ie) {
                LOG.error("Interrupted Exception message : " + ie.getMessage());
                LOG.error("Interrupted Exception : ", ie);
                throw ie;
            } catch (MalformedURLException urlE) {
                LOG.error("Service Exception message : " + urlE.getMessage());
                LOG.error("Service Exception :", urlE);
                throw urlE;
            }catch (ServiceException se) {
                LOG.error("Service Exception message : " + se.getMessage());
                LOG.error("Service Exception :", se);
                throw se;
            } catch(Exception e){
                LOG.error("Exception message : " + e.getMessage());
                LOG.error("Exception :", e);
                if(count < Constants.LOOP_COUNT){
                    try {
                        count++;
                        Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                    }catch (InterruptedException ie) {
                        LOG.error("Interrupted Exception message : " + ie.getMessage());
                        LOG.error("Interrupted Exception : ", ie);
                    }
                }else {
                    throw e;
                }
            }
        }

        return uploadFileId;
    }

    public long submitEssJobRequest(String jobPackageName, String jobDefinitionName, List<String> paramList) throws ServiceException {
        long processId = -1;
        int count = 1;
        while (true) {
            try {
                processId = erpService.submitESSJobRequest(jobPackageName, jobDefinitionName, paramList);
                Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                break;
            } catch (InterruptedException ie) {
                LOG.error("Interrupted Exception message : " + ie.getMessage());
                LOG.error("Interrupted Exception : ", ie);
            } catch (ServiceException se) {
                LOG.error("Service Exception message : " + se.getMessage());
                LOG.error("Service Exception :", se);
                throw se;
            } catch(Exception e){
                LOG.error("Exception message : " + e.getMessage());
                LOG.error("Exception :", e);
                if(count < Constants.LOOP_COUNT){
                    try {
                        count++;
                        Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                    }catch (InterruptedException ie) {
                        LOG.error("Interrupted Exception message : " + ie.getMessage());
                        LOG.error("Interrupted Exception : ", ie);
                    }
                }else {
                    throw e;
                }
            }
        }
        return processId;
    }

    public String checkJobStatus(long processId) throws ServiceException {
        String status = "-1";
        int count = 1;
        while (true) {
            try {
                status = erpService.getESSJobStatus(processId);
                while (true) {
                    if (status.equalsIgnoreCase(Constants.PAUSED) || status.equalsIgnoreCase(Constants.COMPLETED) || status.equalsIgnoreCase(Constants.RUNNING) || status.equalsIgnoreCase(Constants.READY)) {
                        Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                        status = erpService.getESSJobStatus(processId);
                    } else {
                        break;
                    }
                }
                Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                break;
            } catch (InterruptedException ie) {
                LOG.error("Interrupted Exception message : " + ie.getMessage());
                LOG.error("Interrupted Exception : ", ie);
            } catch (ServiceException se) {
                LOG.error("Service Exception message : " + se.getMessage());
                LOG.error("Service Exception :", se);
                throw se;
            } catch(Exception e){
                LOG.error("Exception message : " + e.getMessage());
                LOG.error("Exception :", e);
                if(count < Constants.LOOP_COUNT){
                    try {
                        count++;
                        Thread.sleep(Constants.THREAD_SLEEP_VALUE);
                    }catch (InterruptedException ie) {
                        LOG.error("Interrupted Exception message : " + ie.getMessage());
                        LOG.error("Interrupted Exception : ", ie);
                    }
                }else {
                    throw e;
                }
            }
        }

        return status;
    }

    @PreDestroy
    public void destory(){}
}
