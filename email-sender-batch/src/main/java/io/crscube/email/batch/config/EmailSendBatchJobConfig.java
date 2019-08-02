package io.crscube.email.batch.config;

import io.crscube.email.batch.task.ScheduledBatchMailSendTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by itaesu on 02/08/2019.
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class EmailSendBatchJobConfig {
    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음
    private final ScheduledBatchMailSendTask scheduledBatchMailSendTask;

    @Bean
    public Job startBatchSendMailJob() {
        return jobBuilderFactory.get("startBatchSendMailJob")
                                .start(readEarliestSendMailStep())
                                .build();
    }

    @Bean
    public Step readEarliestSendMailStep() {
        this.scheduledBatchMailSendTask.sendEmails();
        return stepBuilderFactory.get("readEarliestSendMailStep")
                                 .tasklet((contribution, chunkContext) -> {
                                     log.info(">>>>> This is Step1");
                                     this.scheduledBatchMailSendTask.sendEmails();
                                     return RepeatStatus.FINISHED;
                                 })
                                 .build();
    }
}
