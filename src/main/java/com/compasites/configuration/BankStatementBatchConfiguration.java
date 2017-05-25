package com.compasites.configuration;

import com.compasites.tasklets.BankStatementTasklet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by Sobhan Babu on 06-06-2016.
 */
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class BankStatementBatchConfiguration {

    static Logger LOG = LoggerFactory.getLogger(BankStatementBatchConfiguration.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Tasklet getTasklet(){
        return new BankStatementTasklet();
    }

    /*@Scheduled(cron = "${cron.expression.bankstatement}")
    public void perform() throws Exception {
        LOG.info("Bank statement job started ---");

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(bankStatementJob(), param);

        LOG.info("Bank statement job finished with status : " + execution.getStatus());
    }*/

    @Bean
    public Job bankStatementJob() {
        return jobBuilderFactory.get("bankStatementJob")
                .incrementer(new RunIdIncrementer())
                //.listener(invoiceListener())
                .flow(bankStatementStep1())
                .end()
                .build();
    }

    @Bean
    public Step bankStatementStep1() {
        return stepBuilderFactory.get("bankStatementStep1").tasklet(getTasklet()).build();
    }
}
