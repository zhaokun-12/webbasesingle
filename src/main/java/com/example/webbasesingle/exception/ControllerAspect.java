package com.example.webbasesingle.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author alber
 * @Description:全局异常类
 * @date 2020年2月28日 下午12:29:37
 */
@Aspect
@Component
public class ControllerAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller aspect.
     */
    @Pointcut("execution(* com.example.webbasesingle.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * Around 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 执行完AfterAdvice，再转到ThrowingAdvice
     *
     * @param pjp the pjp
     * @return object
     * @throws Throwable the throwable
     */
    @Around(value = "controllerAspect()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        //防止不是http请求的方法，例如：scheduled
        if (ra == null || sra == null) {
            return pjp.proceed();
        }
        HttpServletRequest request = sra.getRequest();
        logger.info("ip:{} Url:{} Method:{} Class_Method:{}.{}", request.getRemoteAddr(),
                request.getRequestURL().toString(), request.getMethod(),
                pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName());

        Object[] args = pjp.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        String parameter = "";
        try {
            parameter = JSONObject.toJSONString(arguments);
        } catch (Exception e) {
            parameter = Arrays.toString(arguments);
        }
        String classType = pjp.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = pjp.getSignature().getName();
        logger.info("rest 请求开始 traceId:{} Host:{}  clazzName:{} methodName:{} 参数：{}", MDC.get("traceId"), request.getRemoteAddr(), clazzName , methodName, parameter);

        long startTime = System.currentTimeMillis();
        try {
            Object response = pjp.proceed();
            // 3.出参打印
            logger.info("Response:{}", response != null ? JSON.toJSONString(response) : "");
            return response;
        } catch (Throwable e) {
            logger.error("Response Error:{}", e);
//            logger.error("Response Error:{}", Arrays.toString(e.getStackTrace()));
            e.getCause();
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("Spend Time : {}ms", (endTime - startTime));
        }
    }
}
