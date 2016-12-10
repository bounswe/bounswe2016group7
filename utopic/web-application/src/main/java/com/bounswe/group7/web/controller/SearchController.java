/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.SearchServiceClient;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.web.domain.ResultTopic;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Batuhan
 */
@RestController
public class SearchController {
    
    @RequestMapping(value = "/topic/{keyword}", method = RequestMethod.GET)
    public ModelAndView search(@PathVariable String keyword, HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView("search");
        SearchServiceClient searchClient = new SearchServiceClient((String) session.getAttribute("token"));
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        try{
            List<Topics> topicsBack = searchClient.searchTopics(keyword);
            List<ResultTopic> topics = new ArrayList();
            for(Topics topicBack : topicsBack){
                ResultTopic resultTopic = new ResultTopic();
                resultTopic.commentNumber = commentClient.getTopicComments(topicBack.getTopicId()).size();
                resultTopic.description = topicBack.getDescription();
                resultTopic.header = topicBack.getHeader();
                resultTopic.id = topicBack.getTopicId();
                resultTopic.rate = topicBack.getRate();
                resultTopic.tags = topicBack.getTags();
                topics.add(resultTopic);
            }
            ObjectMapper mapper = new ObjectMapper();
            String topicList = mapper.writeValueAsString(topics);
            modelAndView.addObject("topics", topicList);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return modelAndView;
    }
}
