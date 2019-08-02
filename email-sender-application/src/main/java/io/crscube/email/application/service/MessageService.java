package io.crscube.email.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by itaesu on 30/07/2019.
 */
@Component @RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String getLocalizedMessage(String messageId, Object... args) {
        return messageSource.getMessage(messageId, args, LocaleContextHolder.getLocale());
    }
}
