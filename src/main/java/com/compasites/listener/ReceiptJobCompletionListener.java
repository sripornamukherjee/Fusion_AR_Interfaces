package com.compasites.listener;

import com.compasites.constants.Constants;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
public class ReceiptJobCompletionListener extends JobExecutionListenerSupport {

    static Logger LOG = LoggerFactory.getLogger(ReceiptJobCompletionListener.class);

    @Value("${receipt.inputDir}")
    private String inputDir;

    @Value("${receipt.inputfile}")
    private String receiptFile;

    @Value("${receipt.processedDir}")
    private String processedDir;

    @Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution){
        File invoiceInputFile = new File(inputDir + receiptFile);
        //String lastLine = null;
        String line = null;
        String previousLine = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        FileWriter fileWriter = null;
        boolean isOperationSuccess = false;

        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = null;
        try {
            if (invoiceInputFile.exists() && invoiceInputFile.length() > 0) {
                fileReader = new FileReader(invoiceInputFile);
                // Always wrap FileReader in BufferedReader.
                bufferedReader = new BufferedReader(fileReader);

                fileWriter = new FileWriter(inputDir + Constants.TEMP_FILE);
                bufferedWriter = new BufferedWriter(fileWriter);

                int i = 0;
                BigDecimal amtSum = new BigDecimal(0);

                while ((line = bufferedReader.readLine()) != null) {
                    i++;

                    if (i == 1) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }

                    if ((previousLine != null) && (i != 1) && (i % 2 != 0)) {
                        bufferedWriter.write(previousLine + Constants.CVS_SEPERATOR + line);
                        bufferedWriter.newLine();
                        String receiptSplit[] = line.split(Constants.CVS_SEPERATOR);
                        if (receiptSplit != null && receiptSplit[4] != null) {
                            amtSum = amtSum.add(new BigDecimal(receiptSplit[4]));
                        }
                    }
                    previousLine = line;
                }

                if (i > 1) {
                    String lastLine[] = previousLine.split(Constants.CVS_SEPERATOR);
                    if (lastLine[3] != null && Integer.parseInt(lastLine[3]) != (i - 2)) {
                        LOG.error("Line count does not match to number of line in file.");
                        throw new RuntimeException("Line count does not match to number of line in file.");
                    }

                    BigDecimal footerAmount = new BigDecimal(lastLine[2]);
                    if (footerAmount.compareTo(amtSum) != 0) {
                        LOG.error("Footer amount does not match to sum of line amount.");
                        throw new RuntimeException("Footer amount does not match to sum of line amount.");
                    }

                    isOperationSuccess = true;
                }
            }
        }catch (IOException ioe){
            LOG.error("IOException : ", ioe);
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (fileReader != null) {
                    fileReader.close();
                }

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

                if(fileWriter != null) {
                    fileWriter.close();
                }

                if (isOperationSuccess){
                    FileUtil.deleteFileNoLenCheck(inputDir + receiptFile);
                    FileUtil.renameFile(inputDir + Constants.TEMP_FILE, inputDir + receiptFile);
                }else {
                    FileUtil.deleteFileNoLenCheck(inputDir + Constants.TEMP_FILE);
                }
            }catch (IOException ioe){
                LOG.error("IOException : ", ioe);
            }
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        File receiptInputFile = new File(inputDir + receiptFile);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED && receiptInputFile.exists()) {
            LOG.debug("!!! Receipt JOB FINISHED! Time to verify the results");

            //after completion of operation input file is moved to processed folder
            FileUtil.moveInputFile(inputDir + receiptFile, processedDir, receiptFile);
        }
    }
}
