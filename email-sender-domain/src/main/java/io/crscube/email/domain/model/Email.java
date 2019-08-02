package io.crscube.email.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by itaesu on 29/07/2019.
 */
@Document @Builder @Getter @ToString
public class Email {
    @Id
    private String id;
    private String title;
    private String content;
    private String sender;
    private MailType mailType;
    private Logo logo;
    private List<String> recipients;
    private Map<String, Object> model;

    @Builder.Default
    private List<Attachment> attachments = new ArrayList<>();

    @Builder.Default
    private EmailStatus status = EmailStatus.WAITING;

    @Builder.Default
    private Map<String, String> additionalInformation = new HashMap<>();

    @Builder.Default
    private LocalDateTime requestedDateTime = LocalDateTime.now();

    private LocalDateTime sentDateTime;

    public void processSuccess(Map<String, String> additionalInformation) {
        if (!CollectionUtils.isEmpty(additionalInformation)) {
            additionalInformation.forEach(this::addAdditionalInformation);
        }

        this.status = EmailStatus.SUCCESS;
        this.sentDateTime = LocalDateTime.now();
    }


    public void processFail(Throwable throwable) {
        if (throwable != null) {
            this.addAdditionalInformation("errorMessage", throwable.getMessage());
        }

        this.status = EmailStatus.FAIL;
        this.requestedDateTime = LocalDateTime.now();
    }

    private void addAdditionalInformation(String key, String value) {
        this.additionalInformation.put(key, value);
    }


    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getLogoPath() {
        return this.logo.getDownloadURL();
    }

    public String getLogoName() {
        return this.logo.getLogoName();
    }

}
