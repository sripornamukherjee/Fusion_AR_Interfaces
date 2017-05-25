package com.compasites.configuration;


import com.compasites.helper.ReceiptInterfacHelper;
import com.compasites.listener.ReceiptCustomWriter;
import com.compasites.listener.ReceiptJobCompletionListener;
import com.compasites.pojo.Receipt;
import com.compasites.processor.ReceiptProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
//@Configuration
//@EnableBatchProcessing
public class ReceiptBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${receipt.inputDir}")
    private String inputDir;

    @Value("${receipt.inputfile}")
    private String receiptFile;

    @Bean
    public FlatFileItemReader<Receipt> receiptReader() {
        FlatFileItemReader<Receipt> reader = new FlatFileItemReader<Receipt>();
        reader.setResource(new FileSystemResource(inputDir + receiptFile));
        reader.setLinesToSkip(1);
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<Receipt>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "invoiceSerialLine","invoiceHeader",
                        "recordType", "customerId","accounttype",
                        "invoiceNumber", "billDate",
                        "docType", "grossTotAmt","payMode",
                        "receiptSerialLine", "collection","collectionNumber",
                        "collectionDate", "collectionAmt","collectionPayMode"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Receipt>() {{
                setTargetType(Receipt.class);
            }});
        }});

        return reader;
    }

    @Bean
    public ReceiptProcessor receiptProcessor() {
        return new ReceiptProcessor();
    }

    @Bean
    public JobExecutionListener receiptListener() {
        return new ReceiptJobCompletionListener();
    }

    @Bean
    public ReceiptCustomWriter customReceiptWriter() {
        return new ReceiptCustomWriter();
    }

    @Bean
    public CompositeItemWriter<Receipt> receiptWriter() {
        List<ItemWriter> list = new ArrayList<ItemWriter>();
        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        compositeItemWriter.setDelegates(list);

        list.add(customReceiptWriter());

        return compositeItemWriter;
    }

    @Bean
    public Job importReceiptJob() {
        return jobBuilderFactory.get("importReceiptJob")
                .incrementer(new RunIdIncrementer())
                .listener(receiptListener())
                .flow(receiptStep1())
                .end()
                .build();
    }

    @Bean
    public Step receiptStep1() {
        return stepBuilderFactory.get("receiptStep1")
                .<Receipt, Receipt> chunk(1)
                .reader(receiptReader())
                .processor(receiptProcessor())
                .writer(receiptWriter())
                .build();
    }

    @Bean
    public ReceiptInterfacHelper receiptInterfacHelper() {
        return new ReceiptInterfacHelper();
    }
}
