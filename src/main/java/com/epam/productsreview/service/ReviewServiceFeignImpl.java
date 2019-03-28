package com.epam.productsreview.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by saurabh on 28/3/19.
 */
@Service("feignImpl")
public class ReviewServiceFeignImpl implements ReviewService{

    @Override
    public ResponseEntity getReviewsByProductId(int productId) {
        return null;
    }

    @Override
    public ResponseEntity deleteReviewsByProductId(int id) {
        return null;
    }

    @Override
    public ResponseEntity updateReviews(Object obj) {
        return null;
    }

    @Override
    public ResponseEntity addReviews(Object obj) {
        return null;
    }
}
