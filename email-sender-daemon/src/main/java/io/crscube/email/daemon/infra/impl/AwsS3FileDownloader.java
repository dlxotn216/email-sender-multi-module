package io.crscube.email.daemon.infra.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import io.crscube.email.daemon.infra.FileDownloader;
import io.crscube.email.domain.model.Attachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * Created by itaesu on 05/08/2019.
 */
@Service @RequiredArgsConstructor
public class AwsS3FileDownloader implements FileDownloader {

    private final AmazonS3 s3Client;

    @Override
    public File download(Attachment attachment, Path dirPath, String fileName) {
        final String bucketName = fileName.split("/")[0];
        final String objectKey = fileName.split("/")[1];
        final S3Object object = this.s3Client.getObject(bucketName, objectKey);

        final File file = dirPath.resolve(objectKey).toFile();
        try (S3ObjectInputStream objectContent = object.getObjectContent()) {
            try (OutputStream os = new FileOutputStream(file)) {
                IOUtils.copy(objectContent, os);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return file;
    }
}
