/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.Reviews;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author myunu
 */
public class ReviewServiceClient extends BaseClient {
    public ReviewServiceClient(){
    }
    
    public ReviewServiceClient(String token)
    {
        super(token);
    }
    
    public Reviews createReview(Reviews request) throws Exception {
        return post(getResource().path("createReview"), new TypeToken<Reviews>() {
        }, request);
    }
    
    public List<Reviews> getUserReviews(Long userId) throws Exception {
        return post(getResource().path("public/getUserReviews"), new TypeToken<List<Reviews>>() {
        }, userId);
    }
    
    public boolean deleteReview(Long reviewId) throws Exception {
        return post(getResource().path("deleteReview"), new TypeToken<Boolean>() {
        }, reviewId);
    }
            
}
