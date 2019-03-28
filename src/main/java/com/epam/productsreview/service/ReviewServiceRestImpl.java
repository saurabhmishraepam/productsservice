package com.epam.productsreview.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by saurabh on 28/3/19.
 */
@Service("restImpl")
public class ReviewServiceRestImpl implements ReviewService{

    private RestTemplate restTemplate=new RestTemplate();
    @Value("${productreviewservicehost}")
    private String endPoint;
    @Value("${productservice.user}")
    private String username;
    @Value("${productservice.password}")
    private String password;
    private String reviewsEndpoint="/reviews/product/";
    private String reviewEndPt="/reviews/";




    public ResponseEntity getReviewsByProductId(int productId){
        ResponseEntity resentity = restTemplate.exchange(endPoint+reviewsEndpoint+productId,
                HttpMethod.GET, getEntity(),String.class);
        return resentity;
    }
    public ResponseEntity deleteReviewsByProductId(int id){
        ResponseEntity resentity = restTemplate.exchange(endPoint+reviewsEndpoint+id,
                HttpMethod.DELETE, getEntity(),String.class);
        return resentity;
    }
    public ResponseEntity updateReviews(Object obj){

        ResponseEntity resentity = null;
        try {
            resentity = restTemplate.exchange(endPoint+reviewEndPt,
                    HttpMethod.PUT, getPostEntity(obj),String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resentity;
    }
    public ResponseEntity addReviews(Object obj){

        ResponseEntity resentity = null;
        try {
            resentity = restTemplate.exchange(endPoint+reviewEndPt,
                    HttpMethod.POST, getPostEntity(obj),String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resentity;
    }

    private HttpEntity<String> getPostEntity(Object obj) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return new HttpEntity<String>(mapper.writeValueAsString(obj), getHeader());
    }

    private HttpEntity<String> getEntity(){
        return new HttpEntity<String>("parameters", getHeader());
    }
    private HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + getBase64Credentials());
        return headers;
    }
    private String getBase64Credentials(){
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }



}
