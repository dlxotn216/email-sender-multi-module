package io.crscube.email.daemon.task;

import io.crscube.email.daemon.service.SearchEarliestEmailService;
import io.crscube.email.daemon.service.SendMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by itaesu on 02/08/2019.
 */

@Component
@Slf4j @RequiredArgsConstructor
public class ScheduledBatchMailSendTask {
    private final SearchEarliestEmailService searchEarliestEmailService;
    private final SendMailService sendMailService;

    @Scheduled(fixedDelay = 1000 * 10, initialDelay = 1000)
    public void sendEmails() {
        log.info("search earliest send mails");
        this.sendMailService.sendEmails(this.searchEarliestEmailService.search());
    }
}
