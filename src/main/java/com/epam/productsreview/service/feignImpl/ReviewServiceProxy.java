package com.epam.productsreview.service.feignImpl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

/**
 * Created by saurabh on 28/3/19.
 */
@FeignClient(name = "reviews")
@SuppressWarnings("rawtypes")
public interface ReviewServiceProxy {
	@RequestMapping(method = RequestMethod.GET, value = "/reviews/product/{productId}")
	@Headers("Content-Type: application/json")
	public ResponseEntity getReviews(@PathVariable(value = "productId") int productId);


	@RequestMapping(method = RequestMethod.POST, value = "/reviews")
	@Headers("Content-Type: application/json")
	public ResponseEntity createReviews(@RequestBody Object review);

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@Headers("Content-Type: application/json")
	public ResponseEntity getReview(@PathVariable("id") int id);

	@PutMapping
	@Headers("Content-Type: application/json")
	public ResponseEntity updateReviews(@RequestBody Object review);

	@DeleteMapping("/{id}")
	@Headers("Content-Type: application/json")
	public ResponseEntity deleteReview(@PathVariable("id") int id);
}
