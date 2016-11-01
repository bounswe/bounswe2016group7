package com.bounswe.group7.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Batuhan
 */
@Entity
public class TopicPacks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_pack_id", nullable = false)
    private Long topicPackId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    private String name;
    
    private int count;
    
    private Date createDate;

    public TopicPacks(String name) {
        this.name = name;
        this.count = 1;
    }

    public TopicPacks() {
    }

    public TopicPacks(Long userId, String name, int count, Date createDate) {
        this.userId = userId;
        this.name = name;
        this.count = count;
        this.createDate = createDate;
    }

    
    public Long getTopicPackId() {
        return topicPackId;
    }

    public void setTopicPackId(Long topicPackId) {
        this.topicPackId = topicPackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
