/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.model.Comments;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Batuhan
 */
@RestController
public class CommentController {
    
    @RequestMapping(value = "/addComment", method=RequestMethod.POST)
    public ModelAndView addComment(HttpServletRequest request, HttpServletResponse response){
        String url = "redirect:/topic";
        HttpSession session = request.getSession();
        CommentServiceClient client = new CommentServiceClient((String) session.getAttribute("token"));
        Comments comment = new Comments();
        String text = request.getParameter("text");
        try{
            comment.setText(text);
            Long topicId = Long.parseLong(request.getParameter("topicId"));
            comment.setTopicId(topicId);
            client.createComment(comment);
            url+="/"+topicId;
        }catch(Exception ex){
            ex.printStackTrace();
            url = "redirect:/";
        }
        ModelAndView modelAndView = new ModelAndView(url);
        return modelAndView;
    }
}
