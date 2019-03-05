package com.epam.productsreview.requestFilters;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * Created by saurabh on 26/2/19.
 */
//todo aop based security sanatiser
@Aspect
@Configuration
public class RequestSanitiozer {

    @Before("execution(* com.epam.productsreview.restController.*.*(..))")
    public void validateRequests(JoinPoint joinPoint){
        System.out.println("calling "+joinPoint.getSignature());
    }


}
