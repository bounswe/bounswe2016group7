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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles the search operations. Search bar's functionality is handled at
 * that controller.
 * 
 * @author Batuhan
 */

@RestController
public class SearchController {
    /**
     * Searches for topics by the given keyword. When a keyword is given to the
     * search bar, search request is sent. Search procedure is handled by the
     * client methods and the results are given in the wanted format.
     * 
     * @param request -> keyword
     * @return result_topics
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
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
