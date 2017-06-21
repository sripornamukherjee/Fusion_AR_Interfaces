package com.compasites.helper;

import com.compasites.constants.Constants;
import com.compasites.utils.DateUtil;
import com.oracle.xmlns.oxp.service.v2.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Sobhan Babu on 07-07-2016.
 */
public class ReportRunHelper {

    static Logger LOG = LoggerFactory.getLogger(ReportRunHelper.class);

    @Value("${oraclefusion.username}")
    private String fusionUsername;

    @Value("${oraclefusion.password}")
    private String fusionPassword;

    @Value("${oraclefusion.reportrun.endpointaddress}")
    private String reportRunEndpointaddress;

    @Value("${oraclefusion.businessunitname}")
    private String businessUnitName;

    public boolean checkCustomerAvailable(String customerId) throws AccessDeniedException_Exception,
            InvalidParametersException_Exception, OperationFailedException_Exception, UnsupportedEncodingException, MalformedURLException{
        boolean isCustomerAvailable = false;
        int count = 1;
        while(true) {
            try {
            /*InvoiceSingleton singleton = InvoiceSingleton.getInstance();
            ReportService reportService = singleton.getReportService();
            if (reportService == null) {*/
                URL url = new URL(reportRunEndpointaddress);
                ReportService_Service repService = new ReportService_Service(url);
                MyHandlerResolver myHandlerResolver = new MyHandlerResolver();
                repService.setHandlerResolver(myHandlerResolver);

                ReportService reportService = repService.getReportService();
                /*singleton.setReportService(reportService);
            }*/

                ReportRequest reportRequest = new ReportRequest();
                reportRequest.setAttributeTemplate(Constants.CUSTOMER_ACCOUNT);
                reportRequest.setReportAbsolutePath(Constants.CUSTOMER_REPORT_ABS_PATH);
                ParamNameValues paramNameVals = new ParamNameValues();
                ArrayOfParamNameValue arrParamNameVal = new ArrayOfParamNameValue();
                List<ParamNameValue> paramNameValList = arrParamNameVal.getItem();
                ParamNameValue customerNum = new ParamNameValue();
                customerNum.setName(Constants.PR_CUSTOMER_NUMBER);
                ArrayOfString values = new ArrayOfString();
                List<String> strValues = values.getItem();
                strValues.add(customerId);
                customerNum.setValues(values);
                paramNameValList.add(customerNum);
                paramNameVals.setListOfParamNameValues(arrParamNameVal);
                reportRequest.setParameterNameValues(paramNameVals);
                reportRequest.setSizeOfDataChunkDownload(2024);
                ReportResponse res = reportService.runReport(reportRequest, fusionUsername, fusionPassword);
                byte repBytes[] = res.getReportBytes();

                String base64Str = byteArrayToBase64(repBytes, repBytes.length);
                java.util.Base64.Decoder dec = java.util.Base64.getDecoder();
                byte[] strdec = dec.decode(base64Str);
                String returnVal = new String(strdec, Constants.UTF8);
                if (returnVal.indexOf(Constants.CUST_ACCOUNT_ID) > 0) {
                    isCustomerAvailable = true;
                }
                break;
            } catch (AccessDeniedException_Exception ade) {
                LOG.error("Access Denied Exception message : " + ade.getMessage());
                LOG.error("Access Denied Exception :", ade);
                throw ade;
            } catch (InvalidParametersException_Exception ipe) {
                LOG.error("Invalid Paramter Exception message : " + ipe.getMessage());
                LOG.error("Invalid Paramter Exception :", ipe);
                throw ipe;
            } catch (OperationFailedException_Exception ofe) {
                LOG.error("Operation failed Exception message : " + ofe.getMessage());
                LOG.error("Operation failed Exception :", ofe);
                throw ofe;
            } catch (UnsupportedEncodingException ue) {
                LOG.error("UnsupportedEncodingException message : " + ue.getMessage());
                LOG.error("UnsupportedEncodingException Exception :", ue);
                throw ue;
            } catch (MalformedURLException urlE) {
                LOG.error("Service Exception message : " + urlE.getMessage());
                LOG.error("Service Exception :", urlE);
                throw urlE;
            } catch (Exception e) {
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

        return isCustomerAvailable;
    }

    public boolean checkInvoiceAvailability(String customerId, String invoiceNumber)
            throws AccessDeniedException_Exception, InvalidParametersException_Exception, OperationFailedException_Exception,
            UnsupportedEncodingException, MalformedURLException{
        boolean isInvoiceAvailable = false;
        int count = 1;
        while(true) {
            try {
                URL url = new URL(reportRunEndpointaddress);
                ReportService_Service repService = new ReportService_Service(url);
                MyHandlerResolver myHandlerResolver = new MyHandlerResolver();
                repService.setHandlerResolver(myHandlerResolver);
                ReportService reportService = repService.getReportService();

                ReportRequest reportRequest = new ReportRequest();
                reportRequest.setAttributeTemplate(Constants.INVOICE_NUMBER_ACCOUNT);
                reportRequest.setReportAbsolutePath(Constants.INVOICE_REPORT_ABS_PATH);
                ParamNameValues paramNameVals = new ParamNameValues();
                ArrayOfParamNameValue arrParamNameVal = new ArrayOfParamNameValue();
                List<ParamNameValue> paramNameValList = arrParamNameVal.getItem();

                ParamNameValue customerNum = new ParamNameValue();
                customerNum.setName(Constants.PR_CUSTOMER_NUMBER);
                ArrayOfString values = new ArrayOfString();
                List<String> strValues = values.getItem();
                strValues.add(customerId);
                customerNum.setValues(values);

                ParamNameValue buName = new ParamNameValue();
                buName.setName(Constants.PR_BU_NAME);
                ArrayOfString values1 = new ArrayOfString();
                List<String> strValues1 = values1.getItem();
                strValues1.add(businessUnitName);
                buName.setValues(values1);

                ParamNameValue trxNumber = new ParamNameValue();
                trxNumber.setName(Constants.PR_TRX_NUMBER);
                ArrayOfString values2 = new ArrayOfString();
                List<String> strValues2 = values2.getItem();
                strValues2.add(invoiceNumber);
                trxNumber.setValues(values2);

                paramNameValList.add(customerNum);
                paramNameValList.add(buName);
                paramNameValList.add(trxNumber);
                paramNameVals.setListOfParamNameValues(arrParamNameVal);
                reportRequest.setParameterNameValues(paramNameVals);
                reportRequest.setSizeOfDataChunkDownload(2024);
                ReportResponse res = reportService.runReport(reportRequest, fusionUsername, fusionPassword);
                byte repBytes[] = res.getReportBytes();

                String base64Str = byteArrayToBase64(repBytes, repBytes.length);
                java.util.Base64.Decoder dec = java.util.Base64.getDecoder();
                byte[] strdec = dec.decode(base64Str);
                String returnVal = new String(strdec, Constants.UTF8);
                
                if (returnVal.indexOf(Constants.CUSTOMER_TRX_ID) > 0) {
                    isInvoiceAvailable = true;
                }
                break;
            } catch (AccessDeniedException_Exception ade) {
                LOG.error("Access Denied Exception message : " + ade.getMessage());
                LOG.error("Access Denied Exception :", ade);
                throw ade;
            } catch (InvalidParametersException_Exception ipe) {
                LOG.error("Invalid Paramter Exception message : " + ipe.getMessage());
                LOG.error("Invalid Paramter Exception :", ipe);
                throw ipe;
            } catch (OperationFailedException_Exception ofe) {
                LOG.error("Operation failed Exception message : " + ofe.getMessage());
                LOG.error("Operation failed Exception :", ofe);
                throw ofe;
            } catch (UnsupportedEncodingException ue) {
                LOG.error("UnsupportedEncodingException message : " + ue.getMessage());
                LOG.error("UnsupportedEncodingException Exception :", ue);
                throw ue;
            } catch (MalformedURLException urlE) {
                LOG.error("Service Exception message : " + urlE.getMessage());
                LOG.error("Service Exception :", urlE);
                throw urlE;
            } catch (Exception e) {
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

        return isInvoiceAvailable;
    }
    
    
    public String getInvoiceLineReference(String customerId, String invoiceNumber, String amount) throws AccessDeniedException_Exception,
    		InvalidParametersException_Exception, OperationFailedException_Exception, UnsupportedEncodingException, MalformedURLException {
    	//LOG.info("Get Invoice Line Ref");
    	//String[] arr = null;
    	String returnVal;
        int count = 1;
          while(true) {
              try {
            	  //String custTrxId = getCustTrxId(customerId, invoiceNumber);            	  
                  URL url = new URL(reportRunEndpointaddress);
                  ReportService_Service repService = new ReportService_Service(url);
                  MyHandlerResolver myHandlerResolver = new MyHandlerResolver();
                  repService.setHandlerResolver(myHandlerResolver);
                  
                  ReportService reportService = repService.getReportService();

                  ReportRequest reportRequest = new ReportRequest();
                  reportRequest.setAttributeTemplate(Constants.INVOICE_LINE_REF_ACCOUNT);
                  reportRequest.setReportAbsolutePath(Constants.INVOICE_LINE_REF_REPORT_ABS_PATH);
                  ParamNameValues paramNameVals = new ParamNameValues();
                  ArrayOfParamNameValue arrParamNameVal = new ArrayOfParamNameValue();
                  List<ParamNameValue> paramNameValList = arrParamNameVal.getItem();
                  

                  ParamNameValue customerNum = new ParamNameValue();
                  customerNum.setName(Constants.PR_CUSTOMER_NUMBER );
                  ArrayOfString values = new ArrayOfString();
                  List<String> strValues = values.getItem();
                  strValues.add(customerId);
                  customerNum.setValues(values);
                  
                  ParamNameValue buName = new ParamNameValue();
                  buName.setName(Constants.PR_BU_NAME);
                  ArrayOfString values1 = new ArrayOfString();
                  List<String> strValues1 = values1.getItem();
                  strValues1.add(businessUnitName);
                  buName.setValues(values1);

                  ParamNameValue trxNumber = new ParamNameValue();
                  trxNumber.setName(Constants.PR_TRX_NUMBER);
                  ArrayOfString values2 = new ArrayOfString();
                  List<String> strValues2 = values2.getItem();
                  strValues2.add(invoiceNumber);
                  trxNumber.setValues(values2);

                  
                  ParamNameValue unitAmount = new ParamNameValue();
                  unitAmount.setName(Constants.PR_UNIT_SELLING_PRICE);
                  ArrayOfString values3 = new ArrayOfString();
                  List<String> strValues3 = values3.getItem();
                  strValues3.add(amount);
                  unitAmount.setValues(values3);

                  paramNameValList.add(customerNum);
                  paramNameValList.add(buName);
                  paramNameValList.add(trxNumber);
                  paramNameValList.add(unitAmount);
                
                  paramNameVals.setListOfParamNameValues(arrParamNameVal);
                  reportRequest.setParameterNameValues(paramNameVals);
                  
                  reportRequest.setSizeOfDataChunkDownload(2024);
                  ReportResponse res = reportService.runReport(reportRequest, fusionUsername, fusionPassword);
                  byte repBytes[] = res.getReportBytes();

                  String base64Str = byteArrayToBase64(repBytes, repBytes.length);
                  java.util.Base64.Decoder dec = java.util.Base64.getDecoder();
                  byte[] strdec = dec.decode(base64Str);
                  returnVal = new String(strdec, Constants.UTF8);
                  //arr = new String[3];
                  break;
              } catch (AccessDeniedException_Exception ade) {
                  LOG.error("Access Denied Exception message : " + ade.getMessage());
                  LOG.error("Access Denied Exception :", ade);
                  throw ade;
              } catch (InvalidParametersException_Exception ipe) {
                  LOG.error("Invalid Paramter Exception message : " + ipe.getMessage());
                  LOG.error("Invalid Paramter Exception :", ipe);
                  throw ipe;
              } catch (OperationFailedException_Exception ofe) {
                  LOG.error("Operation failed Exception message : " + ofe.getMessage());
                  LOG.error("Operation failed Exception :", ofe);
                  throw ofe;
              } catch (UnsupportedEncodingException ue) {
                  LOG.error("UnsupportedEncodingException message : " + ue.getMessage());
                  LOG.error("UnsupportedEncodingException Exception :", ue);
                  throw ue;
              } catch (MalformedURLException urlE) {
                  LOG.error("Service Exception message : " + urlE.getMessage());
                  LOG.error("Service Exception :", urlE);
                  throw urlE;
              } catch (Exception e) {
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

          //return arr;
          return returnVal;
    
    }
    
    public String getInvoiceTaxLineReference(String customerId, String invoiceNumber, String amount) throws AccessDeniedException_Exception,
	InvalidParametersException_Exception, OperationFailedException_Exception, UnsupportedEncodingException, MalformedURLException {
//LOG.info("Get Invoice Line Ref");
//String[] arr = null;
String returnVal;
int count = 1;
  while(true) {
      try {
    	  //String custTrxId = getCustTrxId(customerId, invoiceNumber);            	  
          URL url = new URL(reportRunEndpointaddress);
          ReportService_Service repService = new ReportService_Service(url);
          MyHandlerResolver myHandlerResolver = new MyHandlerResolver();
          repService.setHandlerResolver(myHandlerResolver);
          
          ReportService reportService = repService.getReportService();

          ReportRequest reportRequest = new ReportRequest();
          reportRequest.setAttributeTemplate(Constants.INVOICE_TAX_LINE_REF_ACCOUNT);
          reportRequest.setReportAbsolutePath(Constants.INVOICE_TAX_LINE_REF_REPORT_ABS_PATH);
          ParamNameValues paramNameVals = new ParamNameValues();
          ArrayOfParamNameValue arrParamNameVal = new ArrayOfParamNameValue();
          List<ParamNameValue> paramNameValList = arrParamNameVal.getItem();
          

          ParamNameValue customerNum = new ParamNameValue();
          customerNum.setName(Constants.PR_CUSTOMER_NUMBER );
          ArrayOfString values = new ArrayOfString();
          List<String> strValues = values.getItem();
          strValues.add(customerId);
          customerNum.setValues(values);
          
          ParamNameValue buName = new ParamNameValue();
          buName.setName(Constants.PR_BU_NAME);
          ArrayOfString values1 = new ArrayOfString();
          List<String> strValues1 = values1.getItem();
          strValues1.add(businessUnitName);
          buName.setValues(values1);

          ParamNameValue trxNumber = new ParamNameValue();
          trxNumber.setName(Constants.PR_TRX_NUMBER);
          ArrayOfString values2 = new ArrayOfString();
          List<String> strValues2 = values2.getItem();
          strValues2.add(invoiceNumber);
          trxNumber.setValues(values2);

          
          ParamNameValue unitAmount = new ParamNameValue();
          unitAmount.setName(Constants.PR_UNIT_SELLING_PRICE);
          ArrayOfString values3 = new ArrayOfString();
          List<String> strValues3 = values3.getItem();
          strValues3.add(amount);
          unitAmount.setValues(values3);

          paramNameValList.add(customerNum);
          paramNameValList.add(buName);
          paramNameValList.add(trxNumber);
          paramNameValList.add(unitAmount);
        
          paramNameVals.setListOfParamNameValues(arrParamNameVal);
          reportRequest.setParameterNameValues(paramNameVals);
          
          reportRequest.setSizeOfDataChunkDownload(2024);
          ReportResponse res = reportService.runReport(reportRequest, fusionUsername, fusionPassword);
          byte repBytes[] = res.getReportBytes();

          String base64Str = byteArrayToBase64(repBytes, repBytes.length);
          java.util.Base64.Decoder dec = java.util.Base64.getDecoder();
          byte[] strdec = dec.decode(base64Str);
          returnVal = new String(strdec, Constants.UTF8);
          //arr = new String[3];
          break;
      } catch (AccessDeniedException_Exception ade) {
          LOG.error("Access Denied Exception message : " + ade.getMessage());
          LOG.error("Access Denied Exception :", ade);
          throw ade;
      } catch (InvalidParametersException_Exception ipe) {
          LOG.error("Invalid Paramter Exception message : " + ipe.getMessage());
          LOG.error("Invalid Paramter Exception :", ipe);
          throw ipe;
      } catch (OperationFailedException_Exception ofe) {
          LOG.error("Operation failed Exception message : " + ofe.getMessage());
          LOG.error("Operation failed Exception :", ofe);
          throw ofe;
      } catch (UnsupportedEncodingException ue) {
          LOG.error("UnsupportedEncodingException message : " + ue.getMessage());
          LOG.error("UnsupportedEncodingException Exception :", ue);
          throw ue;
      } catch (MalformedURLException urlE) {
          LOG.error("Service Exception message : " + urlE.getMessage());
          LOG.error("Service Exception :", urlE);
          throw urlE;
      } catch (Exception e) {
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

  //return arr;
  return returnVal;

}

    public String getBatchIdentifier() throws AccessDeniedException_Exception,
            InvalidParametersException_Exception, OperationFailedException_Exception, UnsupportedEncodingException, MalformedURLException{
        String batchId = null;
        int count = 1;
        while(true) {
            try {
                URL url = new URL(reportRunEndpointaddress);
                ReportService_Service repService = new ReportService_Service(url);
                MyHandlerResolver myHandlerResolver = new MyHandlerResolver();
                repService.setHandlerResolver(myHandlerResolver);

                ReportService reportService = repService.getReportService();

                ReportRequest reportRequest = new ReportRequest();
                reportRequest.setAttributeTemplate(Constants.BATCH_IDENTIFIER_ACCOUNT);
                reportRequest.setReportAbsolutePath(Constants.BATCH_IDENTIFIER_ABS_PATH);
                ParamNameValues paramNameVals = new ParamNameValues();
                ArrayOfParamNameValue arrParamNameVal = new ArrayOfParamNameValue();
                List<ParamNameValue> paramNameValList = arrParamNameVal.getItem();
                ParamNameValue customerNum = new ParamNameValue();
                customerNum.setName(Constants.PR_BATCH_NAME);
                ArrayOfString values = new ArrayOfString();
                List<String> strValues = values.getItem();
                strValues.add(Constants.BATCH_ID_INITIAL + DateUtil.getDateyyMMdd());
                customerNum.setValues(values);
                paramNameValList.add(customerNum);
                paramNameVals.setListOfParamNameValues(arrParamNameVal);
                reportRequest.setParameterNameValues(paramNameVals);
                reportRequest.setSizeOfDataChunkDownload(2024);
                ReportResponse res = reportService.runReport(reportRequest, fusionUsername, fusionPassword);
                byte repBytes[] = res.getReportBytes();

                String base64Str = byteArrayToBase64(repBytes, repBytes.length);
                java.util.Base64.Decoder dec = java.util.Base64.getDecoder();
                byte[] strdec = dec.decode(base64Str);
                String returnVal = new String(strdec, Constants.UTF8);
                if (returnVal.indexOf(Constants.BATCH_ID) > 0) {
                    batchId = returnVal.substring(returnVal.indexOf(Constants.BATCH_ID) + 10, returnVal.indexOf(Constants.BATCH_ID_END));

                }
                break;
            } catch (AccessDeniedException_Exception ade) {
                LOG.error("Access Denied Exception message : " + ade.getMessage());
                LOG.error("Access Denied Exception :", ade);
                throw ade;
            } catch (InvalidParametersException_Exception ipe) {
                LOG.error("Invalid Paramter Exception message : " + ipe.getMessage());
                LOG.error("Invalid Paramter Exception :", ipe);
                throw ipe;
            } catch (OperationFailedException_Exception ofe) {
                LOG.error("Operation failed Exception message : " + ofe.getMessage());
                LOG.error("Operation failed Exception :", ofe);
                throw ofe;
            } catch (UnsupportedEncodingException ue) {
                LOG.error("UnsupportedEncodingException message : " + ue.getMessage());
                LOG.error("UnsupportedEncodingException Exception :", ue);
                throw ue;
            } catch (MalformedURLException urlE) {
                LOG.error("Service Exception message : " + urlE.getMessage());
                LOG.error("Service Exception :", urlE);
                throw urlE;
            } catch (Exception e) {
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

        return batchId;
    }

    private static String byteArrayToBase64(byte[] a, int aLen) {
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuffer result = new StringBuffer(resultLen);
        //char intToAlpha[] = intToBase64;
        char intToAlpha[] = Constants.INT_TO_BASE64;
        int inCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            int byte0 = a[inCursor++] & 0xff;
            int byte1 = a[inCursor++] & 0xff;
            int byte2 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[byte0 << 4 & 0x3f | byte1 >> 4]);
            result.append(intToAlpha[byte1 << 2 & 0x3f | byte2 >> 6]);
            result.append(intToAlpha[byte2 & 0x3f]);
        }
        if (numBytesInPartialGroup != 0) {
            int byte0 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[byte0 << 4 & 0x3f]);
                result.append("==");
            } else {
                int byte1 = a[inCursor++] & 0xff;
                result.append(intToAlpha[byte0 << 4 & 0x3f | byte1 >> 4]);
                result.append(intToAlpha[byte1 << 2 & 0x3f]);
                result.append('=');
            }
        }
        return result.toString();
    }
}
