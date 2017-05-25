package com.compasites.configuration;

import com.compasites.listener.CreditnoteCustomWriter;
import com.compasites.listener.InvoiceJobCompletionListener;
import com.compasites.listener.ReceiptCustomWriter;
import com.compasites.listener.ReceiptJobCompletionListener;
import com.compasites.pojo.CreditNote;
import com.compasites.processor.CreditNoteProcessor;
import com.compasites.processor.ReceiptProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by Sobhan Babu on 26-05-2016.
 */
//@Configuration
//@EnableBatchProcessing
public class CreditNoteBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${creditnote.inputDir}")
    private String inputDir;

    @Value("${creditnote.inputfile}")
    private String receiptFile;

    @Bean
    public FlatFileItemReader<CreditNote> creditNoteReader() {
        FlatFileItemReader<CreditNote> reader = new FlatFileItemReader<CreditNote>();
        reader.setResource(new FileSystemResource(inputDir + receiptFile));
        reader.setLinesToSkip(1);
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<CreditNote>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "customerId","creditAmount",
                        "collectionId","comments","collectionDate",
                        "custBankAccntId","billRefNumber"
                });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CreditNote>() {{
                setTargetType(CreditNote.class);
            }});
        }});

        return reader;
    }

    @Bean
    public CreditNoteProcessor creditNoteProcessor() {
        return new CreditNoteProcessor();
    }


    @Bean
    public CreditnoteCustomWriter customCreditNoteWriter() {
        return new CreditnoteCustomWriter();
    }

    @Bean
    public Job creditNoteJob() {
        return jobBuilderFactory.get("creditNoteJob")
                .incrementer(new RunIdIncrementer())
                //.listener(creditNoteListener())
                .flow(creditNoteStep1())
                .end()
                .build();
    }

    @Bean
    public Step creditNoteStep1() {
        return stepBuilderFactory.get("creditNoteStep1")
                .<CreditNote, CreditNote> chunk(5)
                .reader(creditNoteReader())
                .processor(creditNoteProcessor())
                .writer(customCreditNoteWriter())
                .build();
    }
}
