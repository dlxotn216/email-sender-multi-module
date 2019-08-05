package io.crscube.email.domain.model;

import lombok.AllArgsConstructor;

/**
 * Created by itaesu on 05/08/2019.
 */
@AllArgsConstructor
public enum AttachmentType {
    S3("S3"),
    PLAIN("Plain")
    ;

    private String code;

}
