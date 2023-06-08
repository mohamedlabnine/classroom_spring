package com.example.Spring12.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @Pointcut("@annotation(LogAnnotation)")
    public void annotation(){
    }

    @Around(value = "annotation()")
    public ResponseEntity<String> exceptionHandler(ProceedingJoinPoint joinPoint ) throws Throwable {
        try {
            joinPoint.proceed();
        }catch (Exception e){
            return  new ResponseEntity<>("error" , HttpStatusCode.valueOf(500)) ;
        }
        return  new ResponseEntity<>("ok" , HttpStatusCode.valueOf(200));
    }
}
