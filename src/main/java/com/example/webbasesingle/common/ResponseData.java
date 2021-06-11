package com.example.webbasesingle.common;

import com.example.webbasesingle.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.text.MessageFormat;

@Data
@Builder
@AllArgsConstructor
public class ResponseData<T> implements Serializable {

	private static final long serialVersionUID = -4361804081822830622L;
	/**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回消息
     */
    private String message;

    public static <T> ResponseData<T> success() {
        return ResponseData.<T>builder()
                .code(ExceptionEnum.SUCCESS.getCode())
                .message(ExceptionEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> ResponseData<T> success(T data) {
        return ResponseData.<T>builder()
                .code(ExceptionEnum.SUCCESS.getCode())
                .message(ExceptionEnum.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResponseData<T> error() {
        return ResponseData.<T>builder()
                .code(ExceptionEnum.FAILURE.getCode())
                .message(ExceptionEnum.FAILURE.getMessage())
                .build();
    }

    public static <T> ResponseData<T> error(String message) {
        return ResponseData.<T>builder()
                .code(ExceptionEnum.FAILURE.getCode())
                .message(message)
                .build();
    }

    public static <T> ResponseData<T> paramError(String message) {
        return ResponseData.<T>builder()
                .code(ExceptionEnum.PARAM_ERROR.getCode())
                .message(message)
                .build();
    }

    public static <T> ResponseData<T> error(Integer code, String message) {
        return ResponseData.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(ExceptionEnum infoCode) {
        this.code = infoCode.getCode();
        this.message = infoCode.getMessage();
    }

    public ResponseData(ExceptionEnum infoCode, Object ...params) {
        this.code = infoCode.getCode();
        this.message = MessageFormat.format(infoCode.getMessage(), params);
    }

    public void setCode(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }
    public ResponseData(ExceptionEnum infoCode, String message, T data) {
        this.code = infoCode.getCode();
        this.message = message;
        this.data = data;
    }

    public ResponseData() {
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.message = ExceptionEnum.SUCCESS.getMessage();
    }

    public ResponseData(T data) {
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.message = ExceptionEnum.SUCCESS.getMessage();
        this.data = data;
    }
}