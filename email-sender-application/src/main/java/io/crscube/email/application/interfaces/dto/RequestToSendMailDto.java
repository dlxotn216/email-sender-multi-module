package io.crscube.email.application.interfaces.dto;

import io.crscube.email.domain.model.AttachmentType;
import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.model.MailType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.crscube.email.application.constants.ApplicationConstant.SENDER;

/**
 * Created by itaesu on 29/07/2019.
 */
public final class RequestToSendMailDto {

    private RequestToSendMailDto() {
    }

    @Data @AllArgsConstructor @ToString @NoArgsConstructor
    public static class SimpleRequest {
        private String sender;
        private MailType mailType;
        private List<String> titleParameters;
        private List<String> recipients;
        private Map<String, Object> model;
        private List<AttachmentMeta> attachmentMetas;

        public String getSender() {
            return StringUtils.isEmpty(this.sender)
                    ? SENDER
                    : this.sender;
        }

        public List<String> getTitleParameters() {
            return CollectionUtils.isEmpty(titleParameters)
                    ? Collections.emptyList()
                    : this.titleParameters;
        }

        public List<String> getRecipients() {
            return CollectionUtils.isEmpty(recipients)
                    ? Collections.emptyList()
                    : this.recipients;
        }

        public Map<String, Object> getModel() {
            return CollectionUtils.isEmpty(model)
                    ? Collections.emptyMap()
                    : this.model;
        }

        public List<AttachmentMeta> getAttachmentMetas() {
            return CollectionUtils.isEmpty(attachmentMetas)
                    ? Collections.emptyList()
                    : this.attachmentMetas;
        }
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class AttachmentMeta {
        private AttachmentType attachmentType;
        private String attachmentName;
        private String attachmentId;
    }

    @Data @AllArgsConstructor @ToString @NoArgsConstructor
    public static class Response {
        private String messageId;
        private String sender;
        private List<String> recipients;
        private String title;
        private String content;
        private String status;
        private Map<String, String> additionalInformation;

        public static Response from(Email email) {
            return new Response(email.getId(), email.getSender(), email.getRecipients(), email.getTitle(),
                                email.getContent(), email.getStatus().getCode(), email.getAdditionalInformation());
        }

    }
}
