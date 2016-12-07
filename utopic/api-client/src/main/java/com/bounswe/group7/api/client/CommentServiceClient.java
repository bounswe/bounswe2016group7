/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.VotedComments;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author Batuhan
 */
public class CommentServiceClient extends BaseClient{
    
    public CommentServiceClient(){
        
    }
    
    public CommentServiceClient(String token){
        super(token);
    }
    
    public Comments createComment(Comments request) throws Exception {
        return post(getResource().path("createComment"), new TypeToken<Comments>() {
        }, request);
    }
    
    public List<Comments> getTopicComments(Long topicId) throws Exception {
        return post(getResource().path("public/getTopicComments"), new TypeToken<List<Comments>>() {
        }, topicId);
    }
    
    public boolean deleteComment(Long commentId) throws Exception {
        return post(getResource().path("deleteComment"), new TypeToken<Boolean>() {
        }, commentId);
    }
    
    public boolean voteComment(VotedComments votedComment) throws Exception {
        return post(getResource().path("voteComment"), new TypeToken<Boolean>() {
        }, votedComment);
    }
    
    public int getCommentRate(Long commentId) throws Exception {
        return post(getResource().path("getCommentRate"), new TypeToken<Integer>() {
        }, commentId);
    }
}
