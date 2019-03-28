package com.epam.productsreview.service.feignImpl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by saurabh on 28/3/19.
 */
@FeignClient(name="reviews")
public interface ReviewServiceProxy {

    @GetMapping(value = "/product/{productId}")
    public ResponseEntity getReviews(@PathVariable("productId") int productId) ;
}
