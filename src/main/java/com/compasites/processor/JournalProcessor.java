package com.compasites.processor;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.constants.JournalEnum;
import com.compasites.pojo.Journal;
import com.compasites.utils.DateUtil;
import com.compasites.validations.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 07-06-2016.
 */
public class JournalProcessor implements ItemProcessor<Journal, Journal> {

    static Logger LOG = LoggerFactory.getLogger(JournalProcessor.class);

    @Value("${bsh.journal.mappingfile.location}")
    private String mappingFile;

    @Value("${billDate.validDays}")
    private int validDays;

    @Override
    public Journal process(final Journal journal) throws Exception {
        try {
            if(!journal.isLastLine()) {
                //removing comma from customer name
                journal.setCustomerName(CommonValidation.removeComma(journal.getCustomerName()));

                //validation
                DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
                String revenueAccntCode = journal.getRevenueAcCode();
                
                boolean isNotEmptyAmt = CommonValidation.stringNotEmpty(journal.getGrossTotalAmt()) && 
                			CommonValidation.stringNotEmpty(journal.getGstAmount()) &&
                			CommonValidation.stringNotEmpty(journal.getAllocatedRevAmt());
                
                boolean isValidAmt = true;
                if (isNotEmptyAmt) {
                	isValidAmt = CommonValidation.amtDecimalLen(journal.getAllocatedRevAmt()) &&
                			CommonValidation.amtDecimalLen(journal.getGstAmount()) && CommonValidation.amtDecimalLen(journal.getGrossTotalAmt());
                }

                String billDate = journal.getBillDate();
                String courseStartDt = journal.getCourseStartDt();
                boolean isEmptyBillDate = CommonValidation.stringNotEmpty(billDate) & CommonValidation.stringNotEmpty(courseStartDt);
                /*boolean isValidDate = false;
                if (isValidAmt && isNotEmptyAmt && billDate != null && billDate.length() > 0 && courseStartDt != null && courseStartDt.length() > 0) {
                    //isValidDate = CommonValidation.isValidDate(billDate, (validDays * -1));
                	isValidDate = true;
                }*/

                boolean isNotEmptyRevenueAccntCode = CommonValidation.stringNotEmpty(revenueAccntCode);

                if (isNotEmptyAmt && isValidAmt && isNotEmptyRevenueAccntCode) {
                    journal.setGrossTotalAmt(df.format(new BigDecimal(journal.getGrossTotalAmt())));
                    journal.setAllocatedRevAmt(df.format(new BigDecimal(journal.getAllocatedRevAmt())));
                    journal.setValid(true);
                } else {
                    if (!isEmptyBillDate){
                        journal.setErrorMsg(Constants.BILLDATE_EMPTY_ERROR_MSG);
                    } else if(!isNotEmptyAmt){
                    	journal.setErrorMsg(Constants.AMT_EMPTY_ERROR_MSG);
                    } else if(!isValidAmt){
                    	journal.setErrorMsg(Constants.INVALID_AMT_ERROR_MSG);
                    } /*else if (!isValidDate) {
                    	journal.setErrorMsg(Constants.BILL_DATE_ERROR_MSG);
                    }*/ else if (!isNotEmptyRevenueAccntCode) {
                        journal.setErrorMsg(Constants.REVENUEACCOUNTCODE_EMPTY_ERROR_MSG);
                    }
                }
                //validation end

                Interpreter i = new Interpreter(); // Construct an interpreter
                i.source(mappingFile);

                Map<String, String> map = new HashMap<String, String>();
                JournalEnum.setValuesMap(journal, map);

                i.set("map", map);
                i.eval("mapJournalValues(map)");
                map = (Map) i.get("map");

                JournalEnum.setValuesJournal(journal, map);
            }
        } catch (IOException ioe){
            LOG.error("IOException message : " + ioe.getMessage());
            LOG.error("IOException : ", ioe);
            throw ioe;
        } catch (EvalError evlError) {
            LOG.error("EvalError message : " + evlError.getMessage());
            LOG.error("EvalError : ", evlError);
            throw evlError;
        } catch (Exception e) {
            LOG.error("Exception message : " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;
        }

        return journal;
    }
}
