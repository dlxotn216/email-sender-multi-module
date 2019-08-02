package io.crscube.email.batch.infra;

import io.crscube.email.domain.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by itaesu on 29/07/2019.
 */
public interface EmailRepository extends MongoRepository<Email, String> {

}
