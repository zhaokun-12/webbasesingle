package com.example.webbasesingle.common;

import com.example.webbasesingle.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description
 * @Author zhaokun
 * @Date 2021/6/11
 * @Version 1.0
 **/
@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    /**
     * supports方法要返回为true才会执行beforeBodyWrite方法
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //如果返回值类型为Result，则不再进行处理
//        System.out.println("getParameterType" + methodParameter.getParameterType() + "," + methodParameter.getParameterType().equals(ResponseData.class));
//        System.out.println("getGenericParameterType" + methodParameter.getGenericParameterType() + "," + methodParameter.getGenericParameterType().equals(ResponseData.class));
//        System.out.println("getNestedParameterType" + methodParameter.getNestedParameterType() + "," + methodParameter.getNestedParameterType().equals(ResponseData.class));
//        System.out.println("getNestedGenericParameterType" + methodParameter.getNestedGenericParameterType() + "," + methodParameter.getNestedGenericParameterType().equals(ResponseData.class));

        return !methodParameter.getParameterType().equals(ResponseData.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //String类型不能直接包装 ???? ContentType 为“text-plain”,需要转为“application/json; charset=UTF-8“
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResponseData(o));
            } catch (JsonProcessingException e) {
                throw new ServiceException("返回String类型错误");
            }
        }
        return new ResponseData(o);
    }
}
