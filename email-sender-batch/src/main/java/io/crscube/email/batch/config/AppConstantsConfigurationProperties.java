package io.crscube.email.batch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by itaesu on 30/07/2019.
 */
@Component
@Getter @Setter
@ConfigurationProperties(prefix = "app.constants")
public class AppConstantsConfigurationProperties {
    private String awsMessageIdKeyName;
    private String awsMailContentCharset;
}
