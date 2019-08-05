package io.crscube.email.domain.model;

import lombok.Value;

/**
 * Created by itaesu on 31/07/2019.
 */
@Value
public class Attachment {
    private AttachmentType attachmentType;
    private String attachmentName;
    private String attachmentId;

    public String getUniqueName() {
        return attachmentType == AttachmentType.S3
                ? this.attachmentId
                : this.attachmentName;
    }
}
