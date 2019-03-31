package com.epam.productsreview.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by saurabh on 28/3/19.
 */
@Service("restEurekaImpl")
public class ReviewServiceRestEurekaClientImpl implements ReviewService {

    private static final Logger logger= LoggerFactory.getLogger(ReviewServiceRestEurekaClientImpl.class);
    private RestTemplate restTemplate=new RestTemplate();
    @Value("${reviewServiceId}")
    private String reviewServiceId;


    @Value("${productservice.user}")
    private String username;
    @Value("${productservice.password}")
    private String password;
    private String reviewsEndpoint="/reviews/product/";
    private String reviewEndPt="/reviews/";
    @Autowired
    private EurekaClient eurekaCleint;

    public ResponseEntity getReviewsByProductId(int productId) {
        List<InstanceInfo> instances =
                eurekaCleint.getApplication(reviewServiceId).getInstances();
        InstanceInfo instanceInfo = instances.get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
        logger.info(">>>>>>>>>>"+url + reviewsEndpoint + productId);
        
        ResponseEntity resentity =null;
        try {
        	resentity= restTemplate.exchange(url + reviewsEndpoint + productId,
                HttpMethod.GET, getEntity(), String.class);
        }catch(Exception ex) {
        	logger.error("Error in rendering",ex);
        }
        return resentity;
    }
    public ResponseEntity deleteReviewsByProductId(int id){
        List<InstanceInfo> instances =
                eurekaCleint.getApplication(reviewServiceId).getInstances();
        InstanceInfo instanceInfo = instances.get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();


        ResponseEntity resentity = restTemplate.exchange(url+reviewsEndpoint+id,
                HttpMethod.DELETE, getEntity(),String.class);
        return resentity;
    }
    public ResponseEntity updateReviews(Object obj){
        List<InstanceInfo> instances =
                eurekaCleint.getApplication(reviewServiceId).getInstances();
        InstanceInfo instanceInfo = instances.get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
        logger.info(url);
       ResponseEntity resentity = null;
        try {
            resentity = restTemplate.exchange(url+reviewsEndpoint,
                    HttpMethod.PUT, getPostEntity(obj),String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resentity;
    }
    public ResponseEntity addReviews(Object obj){
        List<InstanceInfo> instances =
                eurekaCleint.getApplication(reviewServiceId).getInstances();
        InstanceInfo instanceInfo = instances.get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
        logger.info(url);
        ResponseEntity resentity = null;
        try {
            resentity = restTemplate.exchange(url+reviewsEndpoint,
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
