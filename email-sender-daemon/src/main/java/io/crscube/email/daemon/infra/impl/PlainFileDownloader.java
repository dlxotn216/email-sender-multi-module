package io.crscube.email.daemon.infra.impl;

import io.crscube.email.daemon.config.AppConstantsConfigurationProperties;
import io.crscube.email.daemon.infra.FileDownloader;
import io.crscube.email.domain.model.Attachment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;

/**
 * Created by itaesu on 05/08/2019.
 */
@Component @RequiredArgsConstructor
public class PlainFileDownloader implements FileDownloader {
    private final RestTemplate restTemplate;

    @Override
    public File download(Attachment attachment, Path dirPath, String fileName) {
        return restTemplate.execute(attachment.getAttachmentId(),
                                    HttpMethod.GET, null, clientHttpResponse -> {
                    final File file = dirPath.resolve(fileName).toFile();
                    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(file));
                    return file;
                });
    }
}
