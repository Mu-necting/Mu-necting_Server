package com.munecting.server.global.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.munecting.server.global.config.BaseResponseStatus.SUCCESS;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 요청에 성공한 경우
    public BaseResponse(T result) {
        this.isSuccess = SUCCESS.isSuccess();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    // 추가: 실패 메시지만 입력하여 응답 생성
    public static <T> BaseResponse<T> fail(Boolean isSuccess, String message, int code) {
        return new BaseResponse<>(isSuccess, message, code, null);
    }

    // 추가: 성공 응답 생성
    public static <T> BaseResponse<T> success(T result) {
        return new BaseResponse<>(result);
    }

    // 추가: 성공 응답 생성 (메시지와 코드 추가)
    public static <T> BaseResponse<T> success(String message, int code, T result) {
        return new BaseResponse<>(true, message, code, result);
    }
}
