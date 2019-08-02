package io.crscube.email.daemon.infra;

import io.crscube.email.domain.model.Email;

/**
 * Created by itaesu on 02/08/2019.
 */
public interface BackgroundSendMailTask {
    void send(Email email);
}
