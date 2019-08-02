package io.crscube.email.application.service;

import io.crscube.email.application.interfaces.dto.SentMailSearchDto;
import io.crscube.email.application.repository.ReactiveEmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


/**
 * Created by itaesu on 29/07/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class SentMailSearchService {
    private final ReactiveEmailRepository emailRepository;

    public Flux<SentMailSearchDto> searchAllBySender(String sender) {
        return this.emailRepository.findAllBySender(sender).map(SentMailSearchDto::from);
    }
}
