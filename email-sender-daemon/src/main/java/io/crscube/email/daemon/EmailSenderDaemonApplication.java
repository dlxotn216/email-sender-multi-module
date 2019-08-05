package io.crscube.email.daemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by itaesu on 02/08/2019.
 */
@EnableMongoRepositories(basePackages = "io.crscube.email.domain")
@SpringBootApplication(scanBasePackages = {"io.crscube.email.daemon", "io.crscube.email.domain"})
public class EmailSenderDaemonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailSenderDaemonApplication.class, args);
    }
}
