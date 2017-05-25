package com.compasites.configuration;

import com.compasites.constants.Constants;
import com.compasites.listener.InvoiceCustomWriter;
import com.compasites.listener.InvoiceJobCompletionListener;
import com.compasites.pojo.Customer;
import com.compasites.pojo.Invoice;
import com.compasites.processor.InvoiceProcessor;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.*;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class InvoiceBatchConfiguration {

    static Logger LOG = LoggerFactory.getLogger(InvoiceBatchConfiguration.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${invoice.inputDir}")
    private String inputDir;

    @Value("${invoice.filelookup.dir}")
    private String lookupDir;

    @Value("${invoice.inputfile}")
    private String invoiceFile;

    //need to comment for cron
    @Bean
    public FlatFileItemReader<Invoice> invoiceReader() {
        File dirPath = new File(lookupDir);
        FlatFileItemReader<Invoice> reader = new FlatFileItemReader<Invoice>();
        
        String inputFile = FileUtil.lastModifiedFile(dirPath, Constants.EXTENSION_FILES);
        if(inputFile == null){
            inputFile = Constants.INVOICE_FILENAME;
        }

        Invoice.invoicefile = inputFile;

        
        //reader.setResource(new FileSystemResource(inputDir + invoiceFile));
        reader.setResource(new FileSystemResource(inputDir + inputFile));
        reader.setLinesToSkip(1);
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<Invoice>() {{
            setLineTokenizer(invoiceLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Invoice>() {{
                setTargetType(Invoice.class);
            }});
        }});

        return reader;
    }

    @Bean
    public LineTokenizer invoiceLineTokenizer() {
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
                "invoiceNumber", "billDate",
                "paymentMode", "billingHeaderStatus",
                "grossTotalAmt", "gstAmount",
                "fmsTransactionType","advanceAccntCode",
                "lineSerialNumber","transactionTypeLineItem",                  //line item columns start
                "netTotalAmt",
                "revenueAcCode","discountAmt", "forfeitedAmt", "couponAmt","gstPercent",
                "wdaFundedAmt","sfcFundedAmt", "allocatedRevAmt",
                "halfYear","fullYear","renewalYear",
                "courseStartDt", "wdasfcRefNumber","voucherRefNumber","status"
        };
        rc.setNames(names);
        return rc;
    }

    @Bean
    public InvoiceProcessor invoiceProcessor() {
        return new InvoiceProcessor();
    }

    @Bean
    public JobExecutionListener invoiceListener() {
        return new InvoiceJobCompletionListener();
    }

    @Bean
    public InvoiceCustomWriter customInvoiceWriter() {
        return new InvoiceCustomWriter();
    }

    @Bean
    public CompositeItemWriter<Invoice> invoiceWriter() {
        List<ItemWriter> list = new ArrayList<ItemWriter>();
        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        compositeItemWriter.setDelegates(list);

        //rainterfacelines
        list.add(customInvoiceWriter());


        return compositeItemWriter;
    }

    private FlatFileItemWriter<Invoice> getItemWriter(String fileName, String properties[]){
        FlatFileItemWriter<Invoice> writer = new FlatFileItemWriter<Invoice>();
        writer.setResource(new FileSystemResource(fileName));
        DelimitedLineAggregator<Invoice> delLineAgg = new DelimitedLineAggregator<Invoice>();
        delLineAgg.setDelimiter(",");
        BeanWrapperFieldExtractor<Invoice> fieldExtractor = new BeanWrapperFieldExtractor<Invoice>();
        fieldExtractor.setNames(properties);
        delLineAgg.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(delLineAgg);

        return writer;
    }

    /*@Scheduled(cron = "${cron.expression.invoice}")
    public void perform() throws Exception {
        LOG.info("Invoice job started ---");

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(importInvoiceJob(), param);

        LOG.info("Invoice job finished with status : " + execution.getStatus());
    }*/

    //need to comment for cron
    @Bean
    public Job importInvoiceJob() {
        return jobBuilderFactory.get("importInvoiceJob")
                .incrementer(new RunIdIncrementer())
                .listener(invoiceListener())
                .flow(invoiceJobStep1())
                .end()
                .build();
    }

    //need to comment for cron
    @Bean
    public Step invoiceJobStep1() {
        return stepBuilderFactory.get("invoiceJobStep1")
                .<Invoice, Invoice> chunk(1)
                .reader(invoiceReader())
                .processor(invoiceProcessor())
                .writer(invoiceWriter())
                .build();
    }
}
