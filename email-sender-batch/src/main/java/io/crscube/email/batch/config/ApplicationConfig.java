package io.crscube.email.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesAsyncClient;

/**
 * Created by itaesu on 29/07/2019.
 */
@EnableScheduling
@Configuration
public class ApplicationConfig {

    @Bean
    public SesAsyncClient sesAsyncClient() {
        return SesAsyncClient.builder()
                             .region(Region.US_WEST_2)
                             .credentialsProvider(ProfileCredentialsProvider.builder().build()).build();
    }

}
