package com.compasites.listener;

import com.compasites.constants.Constants;
import com.compasites.helper.ReceiptLockSingleton;
import com.compasites.helper.ReceiptSingleton;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Journal;
import com.compasites.pojo.ReceiptLock;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobhan Babu on 21-06-2016.
 */
public class ReceiptLockCustomWriter implements ItemWriter<ReceiptLock> {

    static Logger LOG = LoggerFactory.getLogger(ReceiptLockCustomWriter.class);

    @Value("${receipt.output.folder}")
    private String outputFolder;

    @Value("${receipt.output.lockboxfile}")
    private String interfaceFile;

    @Value("${receipt.errorfolder}")
    private String errorFolder;

    @Value("${receipt.errorfile}")
    private String filename;

    @Override
    public void write(List<? extends ReceiptLock> items) throws IOException {
        ReceiptLockSingleton singleton = ReceiptLockSingleton.getInstance();
        String fileName = errorFolder + ReceiptLock.receiptFile.substring(0, ReceiptLock.receiptFile.indexOf('.')) + "_" + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        File uploadFile = new File(outputFolder + interfaceFile);
        FileWriter writer = new FileWriter(uploadFile, true);
        BufferedWriter bw = new BufferedWriter(writer);

        try{

            for(ReceiptLock receipt : items) {
                boolean valid = true;
                if (!receipt.isLastLine()) {
                    ArrayList<ReceiptLock> invoiceList = singleton.getValidList(receipt);
                    if (invoiceList == null) {
                        invoiceList = singleton.getErrorList(receipt);
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
                                break;
                            }
                        } else {
                            for (ReceiptLock recipt : invoiceList) {
                            	LOG.info("Writing receipt: "+recipt.getReceiptNumber()+" to error file");
                                errorFileBw.write(recipt.getLineData());
                            }
                        }
                    }

                    //putting the invoice item into singleton
                    singleton.putReceipt(receipt, receipt.isValid());
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
