package io.crscube.email.application.interfaces.controller;

import io.crscube.email.application.interfaces.dto.SentMailSearchDto;
import io.crscube.email.application.service.SentMailSearchService;
import lombok.RequiredArgsConstructor;
import io.crscube.email.application.abstracts.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


/**
 * Created by itaesu on 29/07/2019.
 */
@RestController @RequiredArgsConstructor
public class SentMailSearchController extends AbstractController {
    private final SentMailSearchService sentMailSearchService;

    @GetMapping("/mails")
    public ResponseEntity<Flux<SentMailSearchDto>> searchAllBySender(@RequestParam("sender") String sender) {
        return ResponseEntity.ok(this.sentMailSearchService.searchAllBySender(sender));
    }
}
