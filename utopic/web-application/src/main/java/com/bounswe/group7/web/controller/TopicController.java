package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Topics;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Batuhan
 */
@RestController
public class TopicController {
    
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public ModelAndView showTopic(@RequestParam(value = "topic_id") int topic_id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("topic");
        // TODO client must be implemented for getting topic contents
        return modelAndView;
    }
    
    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    public ModelAndView createTopic(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        Topics topic = new Topics();
        try{
            topic.setContent(request.getParameter("content"));
            topic.setHeader(request.getParameter("header"));
            topic.setTopicPackId(Long.parseLong(request.getParameter("topic_pack_id")));
            Topics topicCreated = client.createTopic(topic);
            attributes.addAttribute("topic_id", topicCreated.getTopicId());
        }catch(Exception ex){
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/topic");
        return modelAndView;
    }
}
