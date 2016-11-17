/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import java.util.Date;

/**
 *
 * @author necil
 */
public class TopicComment {
    public String Username;
    public String Content;
    public double Rating;
    public long UserId;
    public Date CreateDate;
    public long Id;
    
    public TopicComment(String username, String content, Date createDate, double rating, long userId, long id){
        this.Content = content;
        this.CreateDate = createDate;
        this.Rating = rating;
        this.UserId = userId;
        this.Username = username;
        this.Id = id;
    }
}
