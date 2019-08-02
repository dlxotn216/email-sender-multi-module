package io.crscube.email.daemon.service;

import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.model.EmailStatus;
import io.crscube.email.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by itaesu on 02/08/2019.
 */
@Component @RequiredArgsConstructor
public class SearchEarliestEmailService {
    private final EmailRepository emailRepository;

    public List<Email> search() {
        final Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "requestedDateTime"));

        return this.emailRepository.findAllByStatusIsNot(EmailStatus.SUCCESS, pageable).getContent();
    }
}
