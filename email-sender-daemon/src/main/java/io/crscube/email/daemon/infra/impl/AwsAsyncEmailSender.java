package io.crscube.email.daemon.infra.impl;

import io.crscube.email.daemon.config.AppConstantsConfigurationProperties;
import io.crscube.email.daemon.infra.EmailSender;
import io.crscube.email.daemon.infra.ResourceFileManager;
import io.crscube.email.domain.model.Email;
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
import software.amazon.awssdk.services.ses.model.SendRawEmailResponse;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;


/**
 * Created by itaesu on 30/07/2019.
 */
@Slf4j
@Component("AwsAsyncEmailSender") @RequiredArgsConstructor
public class AwsAsyncEmailSender implements EmailSender {
    private final AppConstantsConfigurationProperties properties;
    private final SesAsyncClient sesAsyncClient;
    private final ResourceFileManager resourceFileManager;

    @Async
    @Override
    public CompletableFuture<Email> send(Email email, Executor sendEmailScheduler) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                final SendRawEmailRequest build = getSendEmailRequest(email);
                final SendRawEmailResponse sendEmailResult = this.sesAsyncClient.sendRawEmail(build).get();
                final Map<String, String> successInfo = new HashMap<>();
                successInfo.put(this.properties.getAwsMessageIdKeyName(), sendEmailResult.messageId());
                email.processSuccess(successInfo);
            } catch (Exception e) {
                email.processFail(e);
                log.error("Send mail error {}", e);
            } finally {
                this.resourceFileManager.remove(email.getId());
            }
            return email;
        }, sendEmailScheduler);
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

            email.getAttachments().forEach(attachment -> {
                final File file = this.resourceFileManager.download(email.getId(), attachment);
                try {
                    message.addAttachment(attachment.getAttachmentName(), file);
                } catch (MessagingException e) {
                    log.error("Email attach 첨부 중 에러", e.getMessage(), e);
                }
            });
        };
    }

    private FileSystemResource getLogoFile(Email email) {
        try {
            return new FileSystemResource(new ClassPathResource(email.getLogoPath()).getFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("Logo file not exists " + email.getLogoPath());
        }
    }

}
