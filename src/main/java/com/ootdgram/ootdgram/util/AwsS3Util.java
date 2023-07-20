package com.ootdgram.ootdgram.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;

@Slf4j(topic = "AwsS3")
@Component
public class AwsS3Util {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${s3.url}")
    private String s3Url;

    @Value("${user.default.image}")
    private String defaultUserImageUrl;

    public AwsS3Util(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String upload(MultipartFile multipartFile, String dirName) {
        if (multipartFile.isEmpty())
            return null;

        String s3FileName = dirName + "/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        ObjectMetadata objectMeta = new ObjectMetadata();

        try {
            objectMeta.setContentLength(multipartFile.getInputStream().available());
            amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objectMeta);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }

        URL url = amazonS3.getUrl(bucket, s3FileName);
        return url.toString();
    }

    public void delete(String fileUrl) {
        if (fileUrl == null)
            return;

        String fileKey = URLDecoder.decode(fileUrl.substring(s3Url.length()));
        log.info(fileKey);
        amazonS3.deleteObject(bucket, fileKey);
    }

    public String update(MultipartFile multipartFile, String fileUrl, String dirName) {
        if (!fileUrl.equals(defaultUserImageUrl))
            delete(fileUrl);
        return upload(multipartFile, dirName);
    }
}
