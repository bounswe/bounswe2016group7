/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.Reviews;
import com.bounswe.group7.services.ReviewsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author myunu
 */
@RestController
public class ReviewsController {
    @Autowired
    ReviewsService reviewsService;
    
    @RequestMapping(path = "/createReview", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public Reviews createReview(@RequestBody Reviews review)
    {
        return reviewsService.createReview(review);
    }
    
    @RequestMapping(path = "public/getUserReviews", method = RequestMethod.POST)
    @ResponseBody
    public List<Reviews> getUserReviews(@RequestBody Long userId)
    {
        return reviewsService.getUserReviews(userId);
    }
    
    @RequestMapping(path = "/deleteReview", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public boolean deleteReview(@RequestBody Long reviewId)
    {
        return reviewsService.deleteReview(reviewId);
    }
   
}
