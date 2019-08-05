package io.crscube.email.daemon.infra;

import io.crscube.email.daemon.config.AppConstantsConfigurationProperties;
import io.crscube.email.domain.model.Attachment;
import io.crscube.email.domain.model.AttachmentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by itaesu on 05/08/2019.
 */
@Slf4j
@Component
public class ResourceFileManager {
    private final FileDownloader awsS3FileDownloader;
    private final FileDownloader plainFileDownloader;
    private final AppConstantsConfigurationProperties properties;

    public ResourceFileManager(FileDownloader awsS3FileDownloader,
                               FileDownloader plainFileDownloader,
                               AppConstantsConfigurationProperties properties) {
        this.awsS3FileDownloader = awsS3FileDownloader;
        this.plainFileDownloader = plainFileDownloader;
        this.properties = properties;
    }

    public File download(String id, Attachment attachment) {
        final Path dirPath = getDirectoryPath(id);
        try {
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            throw new IllegalStateException("Directory 생성 중 실패");
        }

        if (attachment.getAttachmentType() == AttachmentType.S3) {
            return this.awsS3FileDownloader.download(attachment, dirPath, attachment.getUniqueName());
        } else if (attachment.getAttachmentType() == AttachmentType.PLAIN) {
            return this.plainFileDownloader.download(attachment, dirPath, attachment.getUniqueName());
        }

        throw new IllegalArgumentException("지원하지 않는 첨부파일 형식");
    }

    public void remove(String id) {
        final Path dirPath = getDirectoryPath(id);
        try {
            FileUtils.deleteDirectory(dirPath.toFile());
        } catch (IOException e) {
            log.error("{} directory 삭제 중 에러", id, e);
        }
    }


    private Path getDirectoryPath(String id) {
        return Paths.get(this.properties.getTempFileDir()).resolve(id);
    }
}
