package io.crscube.email.application.utils;


import io.crscube.email.application.service.MessageService;
import io.crscube.email.domain.model.MailType;

import static io.crscube.email.application.utils.StaticContextAccessor.getBean;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * Created by itaesu on 30/07/2019.
 */
public final class MessageUtils {
    private MessageUtils() {

    }

    public static String getEmailTitle(MailType mailType, Object... args) {
        return getBean(MessageService.class).getLocalizedMessage(mailType.toString(), args);
    }

    public static String getEmailTemplatePath(MailType mailType) {
        final String localizedMessage
                = getBean(MessageService.class).getLocalizedMessage(mailType.toString() + "_TEMPLATE");

        return String.format(localizedMessage, getLocale().toString().toLowerCase());
    }

    public static String getEmailLogoPath(MailType mailType) {
        return getBean(MessageService.class).getLocalizedMessage(mailType.toString() + "_LOGO_PATH");

    }

    public static String getEmailLogoName(MailType mailType) {
        return getBean(MessageService.class).getLocalizedMessage(mailType.toString() + "_LOGO_NAME");

    }

}

