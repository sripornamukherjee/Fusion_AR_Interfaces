package com.compasites.configuration;

import com.compasites.constants.Constants;
import com.compasites.listener.JournalCustomWriter;
import com.compasites.listener.JournalJobCompletionListener;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Journal;
import com.compasites.processor.JournalProcessor;
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
 * Created by Sobhan Babu on 07-06-2016.
 */
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class JournalBatchConfiguration {

    static Logger LOG = LoggerFactory.getLogger(JournalBatchConfiguration.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${journal.inputDir}")
    private String inputDir;

    @Value("${journal.filelookup.dir}")
    private String lookupDir;

    @Value("${journal.inputfile}")
    private String journalFile;

    @Bean
    public FlatFileItemReader<Journal> journalReader() {
        File dirPath = new File(lookupDir);
        String inputFile = FileUtil.lastModifiedFile(dirPath, Constants.EXTENSION_FILES);
        if(inputFile == null){
            inputFile = Constants.JOURNAL_FILENAME;
        }

        Journal.journalFile = inputFile;

        FlatFileItemReader<Journal> reader = new FlatFileItemReader<Journal>();
        //reader.setResource(new FileSystemResource(inputDir + journalFile));
        reader.setResource(new FileSystemResource(inputDir + inputFile));
        reader.setLinesToSkip(1);
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<Journal>() {{
            setLineTokenizer(journalLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Journal>() {{
                setTargetType(Journal.class);
            }});
        }});

        return reader;
    }

    @Bean
    public LineTokenizer journalLineTokenizer() {
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
                "headerSerialNumber", "recordType", "customerId", "customerName"
        };
        rc.setNames(names);
        return rc;
    }

    private LineTokenizer bodyRecordTokenizer() {
        DelimitedLineTokenizer rc = new DelimitedLineTokenizer();
        String[] names = new String[]{
                "srcSysId", "headerSerialNumber", "recordType", "customerId", "customerName",
                "accountType","memClass", "memType","documentType","transactionType",
                "invoiceNumber", "billDate", "paymentMode", "billingHeaderStatus",
                "grossTotalAmt", "gstAmount",
                "fmsTransactionType","advanceAccntCode",
                "lineSerialNumber","transactionTypeLineItem",                  //line item columns start
                "netTotalAmt", "revenueAcCode","discountAmt", "forfeitedAmt",
                "couponAmt","gstPercent", "wdaFundedAmt","sfcFundedAmt",
                "allocatedRevAmt", "halfYear","fullYear","renewalYear",
                "courseStartDt", "wdasfcRefNumber","voucherRefNumber", "status"
        };
        rc.setNames(names);
        return rc;
    }

    @Bean
    public JournalProcessor journalProcessor() {
        return new JournalProcessor();
    }

    @Bean
    public JobExecutionListener journalListener() {
        return new JournalJobCompletionListener();
    }

    @Bean
    public JournalCustomWriter customJournalWriter() {
        return new JournalCustomWriter();
    }

    /*@Scheduled(cron = "${cron.expression.journal}")
    public void perform() throws Exception {
        LOG.info("Journal job started ---");

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(importJournalJob(), param);

        LOG.info("Journal job finished with status :" + execution.getStatus());
    }*/

    @Bean
    public Job importJournalJob() {
        return jobBuilderFactory.get("importJournalJob")
                .incrementer(new RunIdIncrementer())
                .listener(journalListener())
                .flow(journalStep1())
                .end()
                .build();
    }

    @Bean
    public Step journalStep1() {
        return stepBuilderFactory.get("journalStep1")
                .<Journal, Journal> chunk(5)
                .reader(journalReader())
                .processor(journalProcessor())
                .writer(customJournalWriter())
                .build();
    }
}
