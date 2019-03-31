package com.epam.productsreview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.productsreview.service.feignImpl.ReviewServiceProxy;

/**
 * Created by saurabh on 28/3/19.
 */
@Service("feignImpl")
@SuppressWarnings("rawtypes")
public class ReviewServiceFeignImpl implements ReviewService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceFeignImpl.class);
	@Autowired
	ReviewServiceProxy reviewServiceProxy;

	@Override
	public ResponseEntity getReviewsByProductId(int productId) {
		return reviewServiceProxy.getReviews(productId);
	}

	@Override
	public ResponseEntity deleteReviewsByProductId(int id) {
		return reviewServiceProxy.deleteReview(id);
	}

	@Override
	public ResponseEntity updateReviews(Object obj) {
		return reviewServiceProxy.updateReviews(obj);
	}

	@Override
	public ResponseEntity addReviews(Object obj) {
		logger.info("Feignclient adding reviews" + obj);
		return reviewServiceProxy.createReviews(obj);
	}
}
