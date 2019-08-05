package io.crscube.email.daemon.service;

import io.crscube.email.daemon.infra.EmailSender;
import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * Created by itaesu on 02/08/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class SendMailService {
    private final EmailRepository emailRepository;
    private final EmailSender emailSender;
    private final Executor sendEmailScheduler;

    public void sendEmails(List<Email> search) {
        final List<CompletableFuture<Email>> collect = search.stream().map(email -> {
            final CompletableFuture<Email> send = this.emailSender.send(email, sendEmailScheduler);
            send.whenCompleteAsync((email1, throwable) -> this.handle(email), this.sendEmailScheduler);
            return send;
        }).collect(Collectors.toList());
        collect.forEach(CompletableFuture::join);
    }

    private void handle(Email email) {
        log.info(">>> Send Request email is {}", email.getTitle());
        this.emailRepository.save(email);
    }
}
