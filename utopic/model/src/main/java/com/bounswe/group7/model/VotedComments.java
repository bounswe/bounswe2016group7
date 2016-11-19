/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.io.Serializable;
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
public class VotedComments implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTEDCOMMENT_SEQ")
    @SequenceGenerator(name = "VOTEDCOMMENT_SEQ", sequenceName = "VOTEDCOMMENT_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "voted_comment_id", nullable = false)
    private Long votedCommentId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "comment_id", nullable = false)
    private Long commentId;
    
    @Column(name = "rate", nullable = false)
    private Integer rate;

    public VotedComments() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    
    public Long getVotedCommentId() {
        return votedCommentId;
    }

    public void setVotedCommentId(Long votedCommentId) {
        this.votedCommentId = votedCommentId;
    }
    
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
}
