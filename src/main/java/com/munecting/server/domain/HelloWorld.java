package com.munecting.server.domain;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloWorld {
    @ResponseBody
    @GetMapping("")
    public BaseResponse<String> getArchives(){
        return new BaseResponse<>("배포 성공");
    }
}
