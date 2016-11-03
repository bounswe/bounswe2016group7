/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author myunu
 */
@Entity
public class Quizes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", nullable = false)
    private Long quizId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "topic_id", nullable = false)
    private Long topicId;
    
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    
    @Column(name = "name", nullable = false)
    private String name;

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
