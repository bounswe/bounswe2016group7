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
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    @Column(name = "topic_pack_id", nullable = false)
    private Long topicPackId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    private Date createDate;
    
    private int orderBy;

    private String header;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "rate", nullable = false, columnDefinition = "Decimal(3,2) default '0.00'")
    private Double rate;

    public Topics(Long topicId, Long topicPackId, Long userId, String header) {
        this.topicId = topicId;
        this.topicPackId = topicPackId;
        this.userId = userId;
        this.header = header;
    }

    public Topics() {
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }



    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}