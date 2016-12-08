package com.bounswe.group7.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

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
public class TopicPacks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOPICPACK_SEQ")
    @SequenceGenerator(name = "TOPICPACK_SEQ", sequenceName = "TOPICPACK_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "topic_pack_id", nullable = false)
    private Long topicPackId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String name;

    private int count;

    private Date createDate;
    
    @Transient
    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

    public TopicPacks(String name) {
        this.name = name;
        this.count = 0;
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
