package com.compasites.listener;

import com.compasites.constants.Constants;
import com.compasites.helper.JournalSingleton;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Journal;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobhan Babu on 07-06-2016.
 */
public class JournalCustomWriter implements ItemWriter<Journal> {

    static Logger LOG = LoggerFactory.getLogger(JournalCustomWriter.class);

    @Value("${journal.output.folder}")
    private String outputFolder;

    @Value("${journal.output.glinterface}")
    private String interfaceFile;

    @Value("${journal.errorfolder}")
    private String errorFolder;

    @Value("${journal.errorfile}")
    private String filename;

    @Override
    public void write(List<? extends Journal> items) throws IOException {
        JournalSingleton singleton = JournalSingleton.getInstance();
        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        FileWriter writer = new FileWriter(outputFolder + interfaceFile, true);
        BufferedWriter bw = new BufferedWriter(writer);
        try{
            for(Journal journal : items) {
                boolean valid = true;
                if (!journal.isLastLine()) {
                    ArrayList<Journal> journalList = singleton.getValidList(journal);
                    if (journalList == null) {
                        journalList = singleton.getErrorList(journal);
                        valid = false;
                    }
                    if (journalList != null) {
                        if (valid) {
                            int count = 0;
                            for (Journal jornal : journalList) {
                                count++;

                                //for the calculation of Gross total amount = (sum of line amount) * gstpercent
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
                                	//course
                                    bw.write(jornal.getContentCredit());
                                    if (count == journalList.size()){
                                    	//Advance A/C
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

                    //putting the invoice item into singleton
                    singleton.putInvoice(journal, journal.isValid());
                }
            }
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
}

