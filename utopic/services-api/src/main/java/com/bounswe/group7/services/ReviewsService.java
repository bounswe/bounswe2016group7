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
 * This is the service class of the Review Model.
 * @author Yunus Seker
 */
@Service
public class ReviewsService {
    
    @Autowired
    UsersService usersService;
    
    @Autowired
    ReviewsRepository reviewsRepository;
    
    /**
     * This method takes a review, assigns its date,
     * and adds it to the database.
     * @param review Reviews object that holds the text.
     * @return returns the added review.
     */
    public Reviews createReview(Reviews review)
    {
        review.setReviewerId(usersService.getLoggedInUserId());
        review.setDateCreated(new Date());
        return reviewsRepository.save(review);
    }
    
    /**
     * This method takes a user id and returns the reviews written to that user.
     * @param userId the id of the user that reviews written.
     * @return returns the List of Reviews of the user.
     */
    public List<Reviews> getUserReviews(Long userId)
    {
        return reviewsRepository.findByUserId(userId);
    }
    
    /**
     * This method takes a review id and deletes the review from database.
     * @param reviewId the id of the review to be deleted.
     * @return returns true if it can delete the review.
     */
    public boolean deleteReview(Long reviewId)
    {        
        if(reviewsRepository.exists(reviewId))
            reviewsRepository.delete(reviewId);
        else return false;
        return true;
    }
    
}
