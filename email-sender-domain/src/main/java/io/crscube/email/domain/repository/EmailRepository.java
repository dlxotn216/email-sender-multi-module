package io.crscube.email.domain.repository;

import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.model.EmailStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by itaesu on 29/07/2019.
 */
public interface EmailRepository extends MongoRepository<Email, String> {

    Page<Email> findAllByStatusIsNot(EmailStatus emailStatus, Pageable pageable);

}
