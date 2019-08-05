package io.crscube.email.daemon.service;

import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.model.EmailStatus;
import io.crscube.email.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by itaesu on 02/08/2019.
 */
@Service @RequiredArgsConstructor
public class SearchEarliestEmailService {
    @Value("${app.maxProcessCapacity}")
    private int capacity;

    private final EmailRepository emailRepository;

    public List<Email> search() {
        final Pageable pageable = PageRequest.of(0, capacity, Sort.by(Sort.Direction.ASC, "requestedDateTime"));

        return this.emailRepository.findAllByStatusIsNot(EmailStatus.SUCCESS, pageable).getContent();
    }
}
