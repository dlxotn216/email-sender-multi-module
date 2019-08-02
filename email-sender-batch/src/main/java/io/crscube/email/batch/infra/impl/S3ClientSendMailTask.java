package io.crscube.email.batch.infra.impl;

import io.crscube.email.batch.infra.BackgroundSendMailTask;
import io.crscube.email.batch.infra.EmailSender;
import io.crscube.email.domain.model.Email;
import io.crscube.email.batch.infra.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by itaesu on 02/08/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class S3ClientSendMailTask implements BackgroundSendMailTask {
    private final EmailRepository emailRepository;
    private final EmailSender emailSender;

    @Override
    public void send(Email email) {
      log.info(">>> Send Request email is {}", email.toString());
      this.emailSender.send(email)
                      .thenAccept(sendEmailResult -> {
                          email.processSuccess(sendEmailResult);
                          this.emailRepository.save(email);
                      })
                      .exceptionally(throwable -> {
                          email.processFail(throwable);
                          log.error("Send mail error {}", throwable);
                          this.emailRepository.save(email);
                          return null;
                      });
    }
}
