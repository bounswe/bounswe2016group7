/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.VotedComments;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bounswe.group7.repository.CommentsRepository;
import com.bounswe.group7.repository.VotedCommentsRepository;

/**
 * This class is the service class of the Comments Class.
 * @author Yunus Seker
 */
@Service
public class CommentsService {
    
    @Autowired
    UsersService usersService;
    
    @Autowired
    CommentsRepository commentsRepository;
    
    @Autowired
    VotedCommentsRepository votedCommentsRepository;
    /**
     * This method takes a comment object and 
     * creates a comment with the logged in user,
     * sets its rate to zero to initialize it.
     * @param comment object that has text in it.
     * @return returns the added comment object
     */
    public Comments createComment(Comments comment)
    {
        comment.setUserId(usersService.getLoggedInUserId());
        comment.setDateCreated(new Date());
        comment.setRate(0);
        return commentsRepository.save(comment);
    }
    
    /**
     * This method takes a topic id and returns its comments.
     * @param topicId The related topic's id.
     * @return returns a Comment List of the topic.
     */
    public List<Comments> getTopicComments(Long topicId)
    {
        return commentsRepository.findByTopicIdOrderByDateCreatedDesc(topicId);
    }
    
    /**
     * This method takes a comment id and finds it in the database.
     * Deletes it.
     * @param commentId id of the comment to be deleted
     * @return returns true if it could delete. other wise returns false.
     */
    public boolean deleteComment(Long commentId)
    {
        if(commentsRepository.exists(commentId))
            commentsRepository.delete(commentId);
        else return false;
        return true;
    }
    
    /**
     * This method takes a VotedComments object and looks to the database.
     * If user voted that comment previously and the vote is same, it fails to vote.
     * Otherwise it changes the vote to new one and re-calculates its rate.
     * If user never voted previously, it adds the new vote to database.
     * @param votedComment the object that has comment id and vote(1 or -1).
     * @return returns true if the user can vote. Otherwise false.
     */
    public boolean voteComment(VotedComments votedComment)
    {
        VotedComments temp = votedCommentsRepository.findByUserIdAndCommentId(usersService.getLoggedInUserId(), votedComment.getCommentId());
        if(temp != null){
            if(temp.getRate() == votedComment.getRate()) return false;
            Comments comment = commentsRepository.findOne(votedComment.getCommentId());
            comment.setRate(comment.getRate() + votedComment.getRate()*2);
            temp.setRate(votedComment.getRate());
            commentsRepository.save(comment);
            votedCommentsRepository.save(temp);
            return true;
        }
        else{
            votedComment.setUserId(usersService.getLoggedInUserId());
            Comments comment = commentsRepository.findOne(votedComment.getCommentId());
            comment.setRate(comment.getRate() + votedComment.getRate());
            commentsRepository.save(comment);
            votedCommentsRepository.save(votedComment);
            return true;
        }
    }
    /**
     * This method returns the rate of the given comment
     * @param commentId Id of the comment which its rate will be given.
     * @return returns an integer rate of the comment.
     */
    public int getCommentRate(Long commentId)
    {
        return commentsRepository.findOne(commentId).getRate();
    }
    
}
