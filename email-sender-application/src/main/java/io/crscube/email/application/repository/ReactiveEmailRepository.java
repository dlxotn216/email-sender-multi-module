package io.crscube.email.application.repository;

import io.crscube.email.domain.model.Email;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Created by itaesu on 02/08/2019.
 */
public interface ReactiveEmailRepository extends ReactiveMongoRepository<Email, String> {
    Flux<Email> findAllBySender(String sender);
}