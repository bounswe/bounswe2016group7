/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Comments;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bounswe.group7.repository.CommentsRepository;

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
    
    public Comments createComment(Comments comment)
    {
        comment.setUserId(usersService.getLoggedInUserId());
        comment.setDateCreated(new Date());
        comment.setRate(0.0);
        return commentsRepository.save(comment);
    }
    
    public List<Comments> getTopicComments(Long topicId)
    {
        return commentsRepository.findByTopicId(topicId);
    }
    
    public boolean deleteComment(Long commentId)
    {
        if(commentsRepository.exists(commentId))
            commentsRepository.delete(commentId);
        else return false;
        return true;
    }
}
