/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.Comments;
import com.bounswe.group7.services.CommentsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author myunu
 */
@RestController
public class CommentsController {
    @Autowired
    CommentsService commentsService;
    
    @RequestMapping(path = "/createComment", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public Comments createComment(@RequestBody Comments comment)
    {
        return commentsService.createComment(comment);
    }
    
    @RequestMapping(path = "/getTopicComments", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public List<Comments> getTopicComments(@RequestBody Long topicId)
    {
        return commentsService.getTopicComments(topicId);
    }
    
    @RequestMapping(path = "/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public boolean deleteComment(@RequestBody Long commentId)
    {
        return commentsService.deleteComment(commentId);
    }
}
