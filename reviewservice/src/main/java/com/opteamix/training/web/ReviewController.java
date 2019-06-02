package com.opteamix.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.opteamix.training.dal.ReviewRepository;
import com.opteamix.training.domain.Review;

@RestController
public class ReviewController {

    @Autowired
    ReviewRepository dao;
    
    @Autowired
    DiscoveryClient dc;

    @RequestMapping(method=RequestMethod.POST,value="/reviews")
    public ResponseEntity<Review> addNew(@RequestBody Review review) {
        int pid = review.getProductId();
        boolean productExists = false;
        
        List<ServiceInstance> instances = dc.getInstances("productservice");
        String productServiceURL = instances.get(0).getUri().toString();
        
        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<String> response = template.getForEntity(productServiceURL+"/products/"+pid, String.class);
            productExists = response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            
            productExists = false;
        }
        if (productExists) {
            Review saved = dao.save(review);
            return new ResponseEntity<Review>(saved, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reviews")
    public List<Review> getByProductId(@RequestParam("pid") int pid) {
        return dao.findByProductId(pid);
    }

}