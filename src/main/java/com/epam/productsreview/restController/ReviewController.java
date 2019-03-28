package com.epam.productsreview.restController;

import com.epam.productsreview.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by saurabh on 17/3/19.
 */
@Controller
@RequestMapping("/review")
public class ReviewController {
    private static final Logger logger= LoggerFactory.getLogger(ReviewController.class);
    @Autowired
    @Qualifier("feignImpl")
    private ReviewService reviewService;
    @GetMapping("/{productId}")
    public ResponseEntity getReview(@PathVariable("productId") int productId){
        logger.info("Review Service "+productId);
        return reviewService.getReviewsByProductId(productId);
    }
    @PostMapping
    public ResponseEntity addReview(@RequestBody Object obj){
        return reviewService.addReviews(obj);
    }
    @PutMapping
    public ResponseEntity updateReview(@RequestBody Object obj){
        return reviewService.updateReviews(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReview(@PathVariable("id") int id){
        logger.info("Review Service "+id);
        return reviewService.deleteReviewsByProductId(id);
    }


}