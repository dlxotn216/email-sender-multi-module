package io.crscube.email.embedded.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by taesu on 2019-08-03.
 */
@EnableScheduling
@SpringBootApplication
public class EmbeddedMongodbApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmbeddedMongodbApplication.class, args);
    }

    @Scheduled(fixedDelay = 100000000)
    public void daemon(){
        
    }
}
