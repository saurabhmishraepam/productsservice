package com.epam.productsreview.service;

import com.epam.productsreview.service.feignImpl.ReviewServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by saurabh on 28/3/19.
 */
@Service("feignImpl")
public class ReviewServiceFeignImpl implements ReviewService{
    private static final Logger logger= LoggerFactory.getLogger(ReviewServiceFeignImpl.class);
    @Autowired
    ReviewServiceProxy reviewServiceProxy;
    @Override
    public ResponseEntity getReviewsByProductId(int productId) {
        logger.info("Feignclient");
      return   reviewServiceProxy.getReviews(productId);
        //return null;
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
