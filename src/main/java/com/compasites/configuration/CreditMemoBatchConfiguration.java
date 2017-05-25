package com.compasites.configuration;


import com.compasites.constants.Constants;
import com.compasites.listener.CreditMemoCustomWriter;
import com.compasites.listener.CreditMemoJobCompletionListener;
import com.compasites.pojo.CreditMemo;
import com.compasites.pojo.Invoice;
import com.compasites.processor.CreditMemoProcessor;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Sobhan Babu on 02-06-2016.
 */
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class CreditMemoBatchConfiguration {

    static Logger LOG = LoggerFactory.getLogger(CreditMemoBatchConfiguration.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${creditmemo.inputDir}")
    private String inputDir;

    @Value("${creditmemo.filelookup.dir}")
    private String lookupDir;

    @Value("${creditmemo.inputfile}")
    private String creditmemoFile;

    @Bean
    public FlatFileItemReader<CreditMemo> creditMemoReader() {
        File dirPath = new File(lookupDir);
        String inputFile = FileUtil.lastModifiedFile(dirPath, Constants.EXTENSION_FILES);
        if(inputFile == null){
            inputFile = Constants.CREDITMEMO_FILENAME;
        }

        CreditMemo.creditmemoFile = inputFile;

        FlatFileItemReader<CreditMemo> reader = new FlatFileItemReader<CreditMemo>();
        //reader.setResource(new FileSystemResource(inputDir + creditmemoFile));
        reader.setResource(new FileSystemResource(inputDir + inputFile));
        reader.setLinesToSkip(1);
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<CreditMemo>() {{
            setLineTokenizer(creditMemoLineTokenizer());

            setFieldSetMapper(new BeanWrapperFieldSetMapper<CreditMemo>() {{
                setTargetType(CreditMemo.class);
            }});
        }});

        return reader;
    }

    @Bean
    public LineTokenizer creditMemoLineTokenizer() {
        PatternMatchingCompositeLineTokenizer rc = new PatternMatchingCompositeLineTokenizer();

        HashMap<String, LineTokenizer> matchers = new HashMap<>();
        matchers.put("*,*,*,*", endRecordTokenizer());
        matchers.put("*,*,*,*,*", bodyRecordTokenizer());

        rc.setTokenizers(matchers);
        return rc;
    }

    private LineTokenizer endRecordTokenizer() {
        DelimitedLineTokenizer rc = new DelimitedLineTokenizer();
        String[] names = new String[]{
                "headerSerialNumber", "recordType", "customerName", "customerId"
        };
        rc.setNames(names);
        return rc;
    }

    private LineTokenizer bodyRecordTokenizer() {
        DelimitedLineTokenizer rc = new DelimitedLineTokenizer();
        String[] names = new String[]{
                "srcSysId", "headerSerialNumber", "recordType", "customerId", "customerName",
                "accountType","memClass", "memType","documentType","transactionType",
                "creditNoteNumber", "billDate",
                "paymentMode", "billingHeaderStatus",
                "grossTotalAmt", "gstAmount",
                "fmsTransactionType","advanceAccntCode",
                "lineSerialNumber","transactionTypeLineItem",                  //line item columns start
                "netTotalAmt",
                "revenueAcCode","discountAmt", "forfeitedAmt", "couponAmt","gstPercent",
                "wdaFundAmt","sfcFundedAmt", "allocatedRevAmt",
                "halfYear","fullYear","renewalYear",
                "courseStartDt", "wdasfcRefNumber","voucherRefNumber", "status"
        };
        rc.setNames(names);
        return rc;
    }

    @Bean
    public CreditMemoProcessor creditMemoProcessor() {
        return new CreditMemoProcessor();
    }

    @Bean
    public JobExecutionListener creditMemoListener() {
        return new CreditMemoJobCompletionListener();
    }

    @Bean
    public CreditMemoCustomWriter customCreditMemoWriter() {
        return new CreditMemoCustomWriter();
    }

    /*@Scheduled(cron = "${cron.expression.creditmemo}")
    public void perform() throws Exception {
        LOG.info("CreditMemo job started ---");

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(creditMemoJob(), param);

        LOG.info("CreditMemo job finished with status :" + execution.getStatus());
    }*/

    @Bean
    public Job creditMemoJob() {
        return jobBuilderFactory.get("creditMemoJob")
                .incrementer(new RunIdIncrementer())
                .listener(creditMemoListener())
                .flow(creditMemoStep1())
                .end()
                .build();
    }

    @Bean
    public Step creditMemoStep1() {
        return stepBuilderFactory.get("creditMemoStep1")
                .<CreditMemo, CreditMemo> chunk(1)
                .reader(creditMemoReader())
                .processor(creditMemoProcessor())
                .writer(customCreditMemoWriter())
                .build();
    }
}
