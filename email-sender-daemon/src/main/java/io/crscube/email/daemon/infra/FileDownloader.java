package io.crscube.email.daemon.infra;

import io.crscube.email.domain.model.Attachment;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by itaesu on 05/08/2019.
 */
public interface FileDownloader {
    File download(Attachment attachment, Path dirPath1, String fileName);
}
