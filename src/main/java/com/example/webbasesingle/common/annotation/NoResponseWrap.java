package com.example.webbasesingle.common.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author zhaokun
 * @Date 2021/6/11
 * @Version 1.0
 **/
@Target(value=ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoResponseWrap {
}
