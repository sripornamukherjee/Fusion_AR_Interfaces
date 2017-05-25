package com.compasites.configuration;

import com.compasites.constants.Constants;
import com.compasites.helper.ReceiptInterfacHelper;
import com.compasites.listener.ReceiptCustomWriter;
import com.compasites.listener.ReceiptJobCompletionListener;
import com.compasites.listener.ReceiptLockCustomWriter;
import com.compasites.listener.ReceiptLockJobCompletionListener;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Receipt;
import com.compasites.pojo.ReceiptLock;
import com.compasites.processor.ReceiptLockProcessor;
import com.compasites.processor.ReceiptProcessor;
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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sobhan Babu on 20-06-2016.
 */
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class ReceiptLockConfiguration {

    static Logger LOG = LoggerFactory.getLogger(ReceiptLockConfiguration.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${receipt.inputDir}")
    private String inputDir;

    @Value("${receipt.filelookup.dir}")
    private String lookupDir;

    @Value("${receipt.inputfile}")
    private String receiptFile;

    @Bean
    public FlatFileItemReader<ReceiptLock> receiptLockReader() {
        File dirPath = new File(lookupDir);
        String inputFile = FileUtil.lastModifiedFile(dirPath, Constants.EXTENSION_FILES);
        if(inputFile == null){
            inputFile = Constants.RECEIPT_FILENAME;
        }

        ReceiptLock.receiptFile = inputFile;

        FlatFileItemReader<ReceiptLock> reader = new FlatFileItemReader<ReceiptLock>();
        //reader.setResource(new FileSystemResource(inputDir + receiptFile));
        reader.setResource(new FileSystemResource(inputDir + inputFile));
        reader.setLinesToSkip(1);
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<ReceiptLock>() {{
            setLineTokenizer(lineTokenizer());

            setFieldSetMapper(new BeanWrapperFieldSetMapper<ReceiptLock>() {{
                setTargetType(ReceiptLock.class);
            }});
        }});

        return reader;
    }

    @Bean
    public LineTokenizer lineTokenizer() {
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
                "billHeaderIdentifier", "customerId", "customerName", "accountType"
        };
        rc.setNames(names);
        return rc;
    }

    private LineTokenizer bodyRecordTokenizer() {
        DelimitedLineTokenizer rc = new DelimitedLineTokenizer();
        String[] names = new String[]{
                "srcSysId", "billHeaderIdentifier", "customerId","customerName",
                "accountType", "memClass","memType","transactionType", "invoiceNumber",
                "receiptNumber", "collectionDate","accountingDate","billingLineItemIdentifier","paymentMode","collectionStatus",
                "invoiceAmount","receiptAmount","paymentRefNumber","paymentAmount",
                "voucherRefNumber","coupenAmt", "status"
        };
        rc.setNames(names);
        return rc;
    }

    @Bean
    public ReceiptLockProcessor receiptLockProcessor() {
        return new ReceiptLockProcessor();
    }

    @Bean
    public JobExecutionListener receiptLockListener() {
        return new ReceiptLockJobCompletionListener();
    }

    @Bean
    public ReceiptLockCustomWriter customReceiptWriter() {
        return new ReceiptLockCustomWriter();
    }

    @Bean
    public CompositeItemWriter<ReceiptLock> receiptLockWriter() {
        List<ItemWriter> list = new ArrayList<ItemWriter>();
        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        compositeItemWriter.setDelegates(list);

        list.add(customReceiptWriter());

        return compositeItemWriter;
    }

    /*@Scheduled(cron = "${cron.expression.receipt}")
    public void perform() throws Exception {
        LOG.info("Receipt job started ---");

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(importReceiptLockJob(), param);

        LOG.info("Receipt job finished with status : " + execution.getStatus());
    }*/

    @Bean
    public Job importReceiptLockJob() {
        return jobBuilderFactory.get("importReceiptLockJob")
                .incrementer(new RunIdIncrementer())
                .listener(receiptLockListener())
                .flow(receiptLockStep1())
                .end()
                .build();
    }

    @Bean
    public Step receiptLockStep1() {
        return stepBuilderFactory.get("receiptLockStep1")
                .<ReceiptLock, ReceiptLock> chunk(1)
                .reader(receiptLockReader())
                .processor(receiptLockProcessor())
                .writer(receiptLockWriter())
                .build();
    }
}
