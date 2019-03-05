package com.epam.productsreview.exceptionHadlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;

/**
 * Created by saurabh on 26/2/19.
 */
@ControllerAdvice
@RestController
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleProductsException(ProductsException productsException, WebRequest request){

        ResponseEntity<ErrorDetails> entity=new ResponseEntity(
                new ErrorDetails(LocalTime.now(), productsException.getMessage(), productsException.getMessage()), HttpStatus.BAD_REQUEST);
        return entity;
    }


}
