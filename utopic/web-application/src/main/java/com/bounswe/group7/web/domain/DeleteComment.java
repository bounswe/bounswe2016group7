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
public class DeleteComment {
    public Long commentId;
    public Long topicId;

    public DeleteComment(Long commentId, Long topicId) {
        this.commentId = commentId;
        this.topicId = topicId;
    }

}
