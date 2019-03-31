package com.epam.productsreview.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by saurabh on 17/3/19.
 */
@Service
public interface ReviewService {
	@GetMapping("/reviews/product/{productId}")
    public ResponseEntity getReviewsByProductId(int productId);
    public ResponseEntity deleteReviewsByProductId(int id);
    public ResponseEntity updateReviews(Object obj);
    public ResponseEntity addReviews(Object obj);
}
