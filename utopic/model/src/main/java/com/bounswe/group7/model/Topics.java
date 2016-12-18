/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author myunu
 */
@Entity
public class Topics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOPICS_SEQ")
    @SequenceGenerator(name = "TOPICS_SEQ", sequenceName = "TOPICS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    @Column(name = "topic_pack_id", nullable = false)
    private Long topicPackId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "description", nullable = false)
    private String description;

    private Date createDate;

    private int orderBy;

    private String header;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "rate", nullable = false, columnDefinition = "Decimal(3,2) default '0.00'")
    private Double rate;

    private Integer rateCounter;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TOPIC_TAGS",
            uniqueConstraints = @UniqueConstraint(columnNames = {"topic_id", "tag_id"}),
            joinColumns = {
                @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private List<Tags> tags;

    @Transient
    private Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Topics(Long topicId, Long topicPackId, Long userId, String header) {
        this.topicId = topicId;
        this.topicPackId = topicPackId;
        this.userId = userId;
        this.header = header;
    }

    public Topics() {
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Integer getRateCounter() {
        return rateCounter;
    }

    public void setRateCounter(Integer rateCounter) {
        this.rateCounter = rateCounter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
