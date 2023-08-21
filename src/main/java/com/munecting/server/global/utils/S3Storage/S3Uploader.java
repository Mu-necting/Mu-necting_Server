package com.munecting.server.global.utils.S3Storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<String> upload(List<MultipartFile> multipartFiles) {

        List<String> urlList = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            try {
//                InputStream inputStream = new FileInputStream(convertMultipartFileToFile(file));
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                urlList.add(amazonS3.getUrl(bucket, fileName).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return urlList;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        return file;
    }

    public void delete(String imageUrl) {
        String[] split = imageUrl.split("/");
        try {
            amazonS3.deleteObject(bucket, split[3]);
        } catch (AmazonServiceException e) {
            log.error(e.getMessage());
        }
    }
}
