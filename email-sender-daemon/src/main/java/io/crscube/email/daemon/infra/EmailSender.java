package io.crscube.email.daemon.infra;

import io.crscube.email.domain.model.Email;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Created by itaesu on 30/07/2019.
 */
public interface EmailSender {

    CompletableFuture<Email> send(Email email, Executor sendEmailScheduler);
}
