package io.crscube.email.application.interfaces.dto;

import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.model.EmailStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by itaesu on 29/07/2019.
 */
@ToString
@Getter @AllArgsConstructor
public final class SentMailSearchDto {
    private String id;
    private String title;
    private String sender;
    private List<String> recipients;
    private String content;
    private Map<String, Object> model;
    private EmailStatus emailStatus;
    private Map<String, String> additionalInformation = new HashMap<>();
    private String logoName;
    private String logoPath;

    private SentMailSearchDto() {
    }


    public static SentMailSearchDto from(Email email) {
        return new SentMailSearchDto(email.getId(), email.getTitle(), email.getSender(), email.getRecipients(), email.getContent(),
                                     email.getModel(), email.getStatus(), email.getAdditionalInformation(),
                                     email.getLogoName(), email.getLogoPath());
    }
}
