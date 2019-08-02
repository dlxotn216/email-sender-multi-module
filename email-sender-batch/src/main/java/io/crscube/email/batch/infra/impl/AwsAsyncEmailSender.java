package io.crscube.email.batch.infra.impl;

import io.crscube.email.batch.config.AppConstantsConfigurationProperties;
import io.crscube.email.domain.model.Email;
import io.crscube.email.batch.infra.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.ses.SesAsyncClient;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 * Created by itaesu on 30/07/2019.
 */
@Slf4j
@Component("AwsAsyncEmailSender") @RequiredArgsConstructor
public class AwsAsyncEmailSender implements EmailSender {

    private final AppConstantsConfigurationProperties properties;
    private final SesAsyncClient sesAsyncClient;

    @Async
    @Override
    public CompletableFuture<Map<String, String>> send(Email email) {
        try {
            final SendRawEmailRequest build = getSendEmailRequest(email);

            return this.sesAsyncClient.sendRawEmail(build)
                                      .thenApply(sendEmailResult -> {
                                          final Map<String, String> successInfo = new HashMap<>();
                                          successInfo.put(this.properties.getAwsMessageIdKeyName(),
                                                          sendEmailResult.messageId());
                                          return successInfo;
                                      });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private SendRawEmailRequest getSendEmailRequest(Email email) {
        RawMessage rawMessage;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Session session = Session.getDefaultInstance(new Properties());
            MimeMessage message = new MimeMessage(session);
            getMimeMessagePerpetrator(email).prepare(message);

            message.writeTo(outputStream);
            rawMessage = RawMessage.builder().data(SdkBytes.fromByteArray(outputStream.toByteArray())).build();
        } catch (IOException | MessagingException e) {
            log.error("while getMimeMessagePerpetrator exception occurred", e);
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("when call prepare() exception occurred", e);
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        return SendRawEmailRequest.builder()
                                  .destinations(email.getRecipients())
                                  .rawMessage(rawMessage)
                                  .source(email.getSender()).build();
    }

    private MimeMessagePreparator getMimeMessagePerpetrator(Email email) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(email.getTitle());
            message.setText(email.getContent(), true);
            message.setFrom(email.getSender());
            message.setTo(email.getRecipients().stream().map(s -> {
                try {
                    return new InternetAddress(s);
                } catch (AddressException e) {
                    log.error("address is not valid {}", e.getRef(), e);
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList()).toArray(new InternetAddress[]{}));
            message.addInline(email.getLogoName(), getLogoFile(email));
        };
    }

    private FileSystemResource getLogoFile(Email email) {
        try {
            return new FileSystemResource(new ClassPathResource(email.getLogoPath()).getFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("Logo file not exists");
        }
    }

}
