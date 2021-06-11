/**
 * @Description:
 * @author author
 * @date 2020年2月25日 上午10:31:57
 */
package com.example.webbasesingle.exception;


import com.example.webbasesingle.enums.ExceptionEnum;
import lombok.Builder;
import lombok.Data;

import java.text.MessageFormat;

@Data
@Builder
public class ServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer errCode;
    private String message;

    public ServiceException(Integer errCode) {
        this.errCode = errCode;
        this.message = ExceptionEnum.getMessage(errCode);
    }

    public ServiceException(String message) {
        this.errCode = ExceptionEnum.FAILURE.getCode();
        this.message = message;
    }

    public ServiceException(String message, Object... params) {
        this.errCode = ExceptionEnum.FAILURE.getCode();
        this.message = MessageFormat.format(message, params);
    }

    public ServiceException(ExceptionEnum exceptionEnum) {
        this.errCode = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public ServiceException(ExceptionEnum exceptionEnum, String message) {
        this.errCode = exceptionEnum.getCode();
        this.message = message;
    }

    // 自定义异常
    public ServiceException(Integer errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }
}
