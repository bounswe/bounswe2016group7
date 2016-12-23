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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the user review functionalities. In the profile page, there is a
 * reviews section which can be edited by other user's review additions. This
 * controller handles these operations.
 * 
 * @author Batuhan
 */
@RestController
public class ReviewController {
    /**
     * Adds the review by another user into the user's profile. Gets an AJAX
     * request that includes the whole data about the review. Returns the review
     * list of the profiled user to show.
     * 
     * @param request
     * @param review
     * @return reviews of the profiled user
     */
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
