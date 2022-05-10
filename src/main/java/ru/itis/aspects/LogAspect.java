package ru.itis.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class LogAspect {

    private final String LOG_FORMAT = "Exception: {} with cause: {} and message: {}";

    @Before("@annotation(org.springframework.web.bind.annotation.ExceptionHandler) && args(java.lang.Throwable, ..)")
    public void logMethod(JoinPoint joinPoint) {

        Throwable throwable = (Throwable) joinPoint.getArgs()[0];

        log.error(LOG_FORMAT, throwable.getClass(), throwable.getCause(), throwable.getMessage());
    }
}
