package com.epam.productsreview.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by saurabh on 17/3/19.
 */
@Service
public interface ReviewService {
    public ResponseEntity getReviewsByProductId(int productId);
    public ResponseEntity deleteReviewsByProductId(int id);
    public ResponseEntity updateReviews(Object obj);
    public ResponseEntity addReviews(Object obj);
}
