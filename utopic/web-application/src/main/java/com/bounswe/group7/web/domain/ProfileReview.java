/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import java.util.Date;

/**
 *
 * @author Batuhan
 */
public class ProfileReview {
    public Long id;
    public String reviewerName;
    public Date dateCreated;
    public String text;

    public ProfileReview(Long id, String reviewerName, Date dateCreated, String text) {
        this.id = id;
        this.reviewerName = reviewerName;
        this.dateCreated = dateCreated;
        this.text = text;
    }
    
    
}