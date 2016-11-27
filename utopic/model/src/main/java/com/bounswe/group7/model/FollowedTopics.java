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
public class FollowedTopics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOWEDTOPICS_SEQ")
    @SequenceGenerator(name = "FOLLOWEDTOPICS_SEQ", sequenceName = "FOLLOWEDTOPICS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "followed_topic_id", nullable = false)
    private Long followedTopicId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    public FollowedTopics(Long userId, Long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }
    
    public FollowedTopics() {
    }

    public Long getFollowedTopicId() {
        return followedTopicId;
    }

    public void setFollowedTopicId(Long followedTopicId) {
        this.followedTopicId = followedTopicId;
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
    
    
}
