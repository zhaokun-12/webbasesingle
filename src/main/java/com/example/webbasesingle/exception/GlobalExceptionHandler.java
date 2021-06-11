package com.example.webbasesingle.exception;

import com.example.webbasesingle.common.ResponseData;
import com.example.webbasesingle.enums.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseData<String> defaultErrorHandler(Exception e) {
        logger.error("捕获业务异常：", e);
        //判断异常是否是我们定义的异常
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return new ResponseData<String>(serviceException.getErrCode(), serviceException.getMessage());
        } else {
            return new ResponseData<String>(ExceptionEnum.FAILURE.getCode(), Objects.isNull(e)?e.getCause().getMessage():e.getMessage());
        }
    }


//    /**
//     * 请求方式不支持
//     */
//    @ExceptionHandler({TokenException.class})
//    public ResponseData TokenException(TokenException e) {
//        logger.error("捕获业务异常：{},", e.getCode(), e);
//        return new ResponseData(e.getCode(), e.getMessage());
//    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseData handleException(HttpRequestMethodNotSupportedException e) {
        logger.error("捕获业务异常{},{}", ExceptionEnum.FAILURE.getCode(), "不支持的请求方法", e);
        return new ResponseData(ExceptionEnum.FAILURE.getCode(), "不支持的请求方法");
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData exceptionHandler(MethodArgumentNotValidException e) {
        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
        String message = methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("捕获业务异常{},{}", ExceptionEnum.FAILURE.getCode(), message, e);
        return new ResponseData(ExceptionEnum.FAILURE.getCode(), message);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseData validationExceptionHandler(BindException e) {
        BindException bindException = (BindException) e;
        String message = "参数不合法";
        if (!bindException.getBindingResult().getFieldErrors().isEmpty()) {
            message = bindException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        }
        logger.error("捕获业务异常{},{}", ExceptionEnum.FAILURE.getCode(), message, e);
        return new ResponseData(ExceptionEnum.FAILURE.getCode(), message);
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseData readableException(HttpMessageNotReadableException e){
        logger.error("捕获业务异常:{}",ExceptionEnum.PARAMS_ERROR.getMessage()+e.getMessage());
        return ResponseData.error(ExceptionEnum.PARAMS_ERROR.getMessage());
    }

//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public ResponseData violationException(ConstraintViolationException e) {
//        String msg = "参数异常";
//        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
//            msg = constraintViolation.getMessage();
//            break;
//        }
//        logger.error("捕获业务异常：{}，{}", ExceptionEnum.PARAMS_ERROR.getMessage(), msg, e.getMessage());
//        return new ResponseData<>(ExceptionEnum.PARAMS_ERROR.getCode(), msg);
//    }

    /**
     * 缺少请求参数异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseData handleException(MissingServletRequestParameterException e) {
        logger.error("捕获业务异：{}", ExceptionEnum.PARAMS_ERROR.getMessage(), e);
        return new ResponseData(ExceptionEnum.PARAMS_ERROR);
    }
}
