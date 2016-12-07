/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.ReviewServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Reviews;
import com.bounswe.group7.web.domain.ProfileReview;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Batuhan
 */
public class ReviewController {
    
    @RequestMapping(value = "/addreview", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addReview(HttpServletRequest request,@RequestBody Reviews review){
        ReviewServiceClient reviewClient = new ReviewServiceClient((String) request.getSession().getAttribute("token"));
        UserServiceClient userClient = new UserServiceClient((String) request.getSession().getAttribute("token"));
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<ProfileReview> profileReviewList = new ArrayList<ProfileReview>();
            reviewClient.createReview(review);
            List <Reviews> reviewList = reviewClient.getUserReviews(review.getUserId());
            for(Reviews temp: reviewList){
                String username = userClient.getUser(temp.getReviewerId()).getUsername();
                profileReviewList.add(new ProfileReview(temp.getReviewId(), temp.getReviewerId(), username, temp.getDateCreated(), temp.getText()));
            }
            String profileReviews = mapper.writeValueAsString(profileReviewList);
            return profileReviews;
            
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
}
