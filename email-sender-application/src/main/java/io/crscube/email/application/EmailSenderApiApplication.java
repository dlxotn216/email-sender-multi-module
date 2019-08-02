package io.crscube.email.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication(scanBasePackages = {"io.crscube.email.domain",
        "io.crscube.email.application"})
public class EmailSenderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApiApplication.class, args);
    }

}
