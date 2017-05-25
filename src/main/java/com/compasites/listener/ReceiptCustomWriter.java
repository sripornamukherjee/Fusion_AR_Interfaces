package com.compasites.listener;

import com.compasites.constants.Constants;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.helper.ReceiptInterfacHelper;
import com.compasites.pojo.Receipt;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Sobhan Babu on 20-05-2016.
 */
public class ReceiptCustomWriter implements ItemWriter<Receipt> {

    static Logger LOG = LoggerFactory.getLogger(ReceiptCustomWriter.class);


    @Autowired
    ReceiptInterfacHelper receiptHelper;

    @Value("${receipt.errorfolder}")
    private String errorFolder;

    @Value("${receipt.errorfile}")
    private String filename;

    @Override
    public void write(List<? extends Receipt> items) throws IOException{
        String fileName = errorFolder + filename + DateUtil.getDateTime() + Constants.CSV;
        FileWriter writer = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(writer);
        try {
            for (Receipt receipt : items) {
                try {
                    if (receipt.isValid()) {
                        receiptHelper.createReceipt(receipt);
                    } else {
                        //any exception occurs need to put into errorfile in error folder
                        bw.write(receipt.getLine1Content());
                        bw.write(receipt.getLine2Content());
                    }
                } catch (Exception e) {
                    LOG.error("Exception message  : " + e.getMessage());
                    LOG.error("Exception : ", e);
                }
            }
        }finally {
            bw.flush();
            bw.close();

            //to delete the error file if it is empty
            FileUtil.deleteFile(fileName);
        }
    }
}
