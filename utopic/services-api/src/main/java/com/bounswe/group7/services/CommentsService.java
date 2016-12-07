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
 *
 * @author myunu
 */
@Service
public class CommentsService {
    
    @Autowired
    UsersService usersService;
    
    @Autowired
    CommentsRepository commentsRepository;
    
    @Autowired
    VotedCommentsRepository votedCommentsRepository;
    
    public Comments createComment(Comments comment)
    {
        comment.setUserId(usersService.getLoggedInUserId());
        comment.setDateCreated(new Date());
        comment.setRate(0);
        return commentsRepository.save(comment);
    }
    
    public List<Comments> getTopicComments(Long topicId)
    {
        return commentsRepository.findByTopicIdOrderByDateCreatedDesc(topicId);
    }
    
    public boolean deleteComment(Long commentId)
    {
        if(commentsRepository.exists(commentId))
            commentsRepository.delete(commentId);
        else return false;
        return true;
    }
    
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
    public int getCommentRate(Long commentId)
    {
        return commentsRepository.findOne(commentId).getRate();
    }
    
}
