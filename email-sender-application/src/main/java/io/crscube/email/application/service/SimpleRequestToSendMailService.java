package io.crscube.email.application.service;

import freemarker.core.InvalidReferenceException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import io.crscube.email.application.interfaces.dto.RequestToSendMailDto.Response;
import io.crscube.email.application.interfaces.dto.RequestToSendMailDto.SimpleRequest;
import io.crscube.email.application.repository.ReactiveEmailRepository;
import io.crscube.email.domain.model.Attachment;
import io.crscube.email.domain.model.Email;
import io.crscube.email.domain.model.Logo;
import io.crscube.email.domain.model.MailType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfig;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static io.crscube.email.application.utils.MessageUtils.*;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/**
 * Created by itaesu on 29/07/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class SimpleRequestToSendMailService {
    private final FreeMarkerConfig freemarkerConfig;
    private final ReactiveEmailRepository emailRepository;

    public Mono<Response> requestToSend(SimpleRequest request) {
        final Mono<Email> save = this.emailRepository.save(build(request));
        return save.map(Response::from);
    }


    private Email build(SimpleRequest request) {
        final MailType mailType = request.getMailType();

        return Email.builder().sender(request.getSender())
                    .mailType(mailType)
                    .title(getEmailTitle(mailType, request.getTitleParameters().toArray()))
                    .content(getContent(request))
                    .model(request.getModel())
                    .logo(new Logo(getEmailLogoName(mailType), getEmailLogoPath(mailType)))
                    .recipients(request.getRecipients())
                    .attachments(request.getAttachmentMetas().stream()
                                        .map(meta -> new Attachment(meta.getAttachmentType(),
                                                                    meta.getAttachmentName(),
                                                                    meta.getAttachmentId()))
                                        .collect(toList()))
                    .build();
    }

    private String getContent(SimpleRequest request) {
        try {
            final Template template
                    = freemarkerConfig.getConfiguration().getTemplate(getEmailTemplatePath(request.getMailType()));
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, request.getModel());
        } catch (IOException | TemplateException e) {
            if (e instanceof InvalidReferenceException) {
                final String errorMessage
                        = format("Require parameter [%s] is not present",
                                 ((InvalidReferenceException) e).getBlamedExpressionString());

                log.error("Cannot load template cause: {}", errorMessage);
                throw new IllegalArgumentException(errorMessage);
            } else if (e instanceof TemplateNotFoundException) {
                final String errorMessage
                        = format("Could not load template %s", ((TemplateNotFoundException) e).getTemplateName());

                log.error("Cannot load template cause: {}", errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }

            log.error("Cannot load template cause: {}", e);
            throw new IllegalArgumentException(e);

        }
    }

}
