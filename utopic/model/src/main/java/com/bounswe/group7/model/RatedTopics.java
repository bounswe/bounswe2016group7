/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author myunu
 */
@Entity
public class RatedTopics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATEDTOPICS_SEQ")
    @SequenceGenerator(name = "RATEDTOPICS_SEQ", sequenceName = "RATEDTOPICS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "rated_topic_id", nullable = false)
    private Long ratedTopicId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    @Column(name = "rate", nullable = false) // rate [1-5]
    private Integer rate;

    public RatedTopics() {
    }

    public Long getRatedTopicId() {
        return ratedTopicId;
    }

    public void setRatedTopicId(Long ratedTopicId) {
        this.ratedTopicId = ratedTopicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    
    
}
