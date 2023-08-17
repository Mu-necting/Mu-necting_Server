package com.munecting.server.global.utils.S3Storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UploadImageS3 {
    @Autowired
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.image.bucket:munecting}")
    private String bucket;

    // 업로드
    public String upload(MultipartFile uploadFile, String filePath, String saveFileName) throws AmazonServiceException, SdkClientException, IOException {
        String fileName = filePath + "/" + saveFileName;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(uploadFile.getContentType());
        objectMetadata.setContentLength(uploadFile.getSize());

        try {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // public으로 권한 설정
        } catch(IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
    // 조회
    public String getS3(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    // 삭제
    public void remove(String fileName) {
        if (!amazonS3.doesObjectExist(bucket, fileName)) {
            throw new AmazonS3Exception("Object " + fileName + " does not exist!");
        }
        amazonS3.deleteObject(bucket, fileName);
    }
}
