package com.epam.productsreview.requestFilters;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Created by saurabh on 26/2/19.
 */
//todo aop based security sanatiser
@Aspect
@Configuration
public class RequestSanitiozer {
    private static Logger logger= LoggerFactory.getLogger(RequestSanitiozer.class);
    @Before("execution(* com.epam.productsreview.restController.*.*(..))")
    public void validateRequests(JoinPoint joinPoint){
        logger.debug("Method invoked ",joinPoint.getSignature());
    }


}
