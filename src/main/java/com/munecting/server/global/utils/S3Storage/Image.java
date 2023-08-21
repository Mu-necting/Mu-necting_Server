package com.munecting.server.global.utils.S3Storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String originalFileName;
    // 업로드 파일 경로
    private String uploadFilePath;
}
