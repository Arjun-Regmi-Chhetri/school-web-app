package com.school.school.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@Aspect
public class LoggerAspect {


    @Around("execution(* com.school.school..*.*(..))")
    public Object log(ProceedingJoinPoint jointPoint) throws Throwable {
        log.info("{} method execution start", jointPoint.getSignature().toString());
        Instant start = Instant.now();
        Object result = jointPoint.proceed();
        Instant end = Instant.now();
        long timelapsed = Duration.between(start,end).toMillis();
        log.info("Time took to complete {} method is {}", jointPoint.getSignature().getName(), timelapsed);
        log.info("{} method execution completed", jointPoint.getSignature().getName());
        return result;
    }

    @AfterThrowing(value = "execution(* com.school.school.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {

        log.error("{} An exception happened due to : {}", joinPoint.getSignature(), ex.getMessage());

    }


}
