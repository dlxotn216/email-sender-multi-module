package io.crscube.email.batch.task;

import io.crscube.email.batch.infra.BackgroundSendMailTask;
import io.crscube.email.batch.service.SearchEarliestEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by itaesu on 02/08/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class ScheduledBatchMailSendTask {
    private final SearchEarliestEmailService searchEarliestEmailService;
    private final BackgroundSendMailTask backgroundSendMailTask;

    public void sendEmails() {
        log.info("search earliest send mails");
        this.searchEarliestEmailService.search().forEach(this.backgroundSendMailTask::send);
    }
}
