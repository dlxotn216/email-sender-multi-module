package io.crscube.email.daemon.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
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

    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard()
                                    .withCredentials(new com.amazonaws.auth.profile.ProfileCredentialsProvider())
                                    .withRegion(Regions.AP_NORTHEAST_2).build();
    }

    @Bean
    public TransferManager transferManager() {
        return TransferManagerBuilder.standard()
                                     .withS3Client(amazonS3Client())
                                     .build();
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(1);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
