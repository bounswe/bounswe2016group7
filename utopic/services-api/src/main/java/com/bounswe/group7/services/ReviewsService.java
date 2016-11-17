/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Reviews;
import com.bounswe.group7.repository.ReviewsRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author myunu
 */
@Service
public class ReviewsService {
    
    @Autowired
    UsersService usersService;
    
    @Autowired
    ReviewsRepository reviewsRepository;
    
    public Reviews createReview(Reviews review)
    {
        review.setReviewerId(usersService.getLoggedInUserId());
        review.setDateCreated(new Date());
        return reviewsRepository.save(review);
    }
    
    public List<Reviews> getUserReviews(Long userId)
    {
        return reviewsRepository.findByUserId(userId);
    }
    
    public boolean deleteReview(Long reviewId)
    {        
        if(reviewsRepository.exists(reviewId))
            reviewsRepository.delete(reviewId);
        else return false;
        return true;
    }
    
}
