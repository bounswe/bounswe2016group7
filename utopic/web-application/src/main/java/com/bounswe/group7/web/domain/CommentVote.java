/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

/**
 *
 * @author Batuhan
 */
public class CommentVote {
    public Long commentId;
    public int updown;

    public CommentVote() {
    }

    public CommentVote(Long commentId, int updown) {
        this.commentId = commentId;
        this.updown = updown;
    }
    
}
