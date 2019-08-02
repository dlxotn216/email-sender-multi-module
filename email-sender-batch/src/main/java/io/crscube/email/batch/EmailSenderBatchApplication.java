package io.crscube.email.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by itaesu on 02/08/2019.
 */
@EnableBatchProcessing
@SpringBootApplication
public class EmailSenderBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailSenderBatchApplication.class, args);
    }
}
