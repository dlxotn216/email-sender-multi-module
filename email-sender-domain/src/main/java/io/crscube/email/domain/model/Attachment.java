package io.crscube.email.domain.model;

import lombok.Value;

/**
 * Created by itaesu on 31/07/2019.
 */
@Value
public class Attachment {
    private String attachmentName;
    private String downloadURL;
}
