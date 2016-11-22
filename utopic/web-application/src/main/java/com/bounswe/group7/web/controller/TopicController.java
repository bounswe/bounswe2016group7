package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.web.domain.TopicComment;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Batuhan
 */
@RestController
public class TopicController {
    
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public ModelAndView showTopic(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("topic");
        HttpSession session = request.getSession();
        TopicServiceClient topicClient = new TopicServiceClient((String) session.getAttribute("token"));
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        UserServiceClient userClient = new UserServiceClient((String) session.getAttribute("token"));
        ObjectMapper mapper = new ObjectMapper();
        List <TopicComment> topicCommentList = new ArrayList<TopicComment>();
        
        try {
            Topics topic = topicClient.getTopic(id);
            Users owner = userClient.getUser(topic.getUserId());
            TopicPacks topicPack = topicClient.getTopicPack(topic.getTopicPackId());
            List<Comments> comments = commentClient.getTopicComments(id);
            
            for(Comments temp: comments) {
                String username = userClient.getUser(temp.getUserId()).getUsername();
                TopicComment topicComment = new TopicComment(username, temp.getText(), temp.getDateCreated(), temp.getRate(), temp.getUserId(), temp.getCommentId());
                topicCommentList.add(topicComment);
            }
            
            String commentsJson = mapper.writeValueAsString(topicCommentList);
            
            modelAndView.addObject("pack",topicPack);
            modelAndView.addObject("topic", topic);
            modelAndView.addObject("owner", owner);
            modelAndView.addObject("comments", commentsJson);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/createTopic", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ModelAndView createTopic(HttpServletRequest request, @RequestBody Topics topic, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        ModelAndView modelAndView;
        try{
            topic.setDescription("Description");
            Topics topicCreated = client.createTopic(topic);
            modelAndView = new ModelAndView("redirect:/topic/"+topicCreated.getTopicId()+"/");
        }catch(Exception ex){
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
            modelAndView = new ModelAndView("redirect:/");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/addcomment", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addComment(HttpServletRequest request,@RequestBody Comments comment) {
        HttpSession session = request.getSession();
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        UserServiceClient userClient = new UserServiceClient((String) session.getAttribute("token"));
        ObjectMapper mapper = new ObjectMapper();
        List <TopicComment> topicCommentList = new ArrayList<TopicComment>();
        String topicComments = "";
        try{
            commentClient.createComment(comment);
            List <Comments> comments = commentClient.getTopicComments(comment.getTopicId());
            for(Comments temp: comments) {
                String username = userClient.getUser(temp.getUserId()).getUsername();
                TopicComment topicComment = new TopicComment(username, temp.getText(), temp.getDateCreated(), temp.getRate(), temp.getUserId(), temp.getCommentId());
                topicCommentList.add(topicComment);
            }
            topicComments = mapper.writeValueAsString(topicCommentList);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        return topicComments;
    }
}
