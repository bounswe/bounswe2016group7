package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Topics;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public ModelAndView showTopic(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("topic");
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        try {
            Topics topic = client.getTopic(id);
            modelAndView.addObject("topic", topic);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        // TODO client must be implemented for getting topic contents
        return modelAndView;
    }
    
    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    public ModelAndView createTopic(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        Topics topic = new Topics();
        ModelAndView modelAndView;
        try{
            topic.setContent(request.getParameter("content"));
            topic.setHeader(request.getParameter("header"));
            topic.setTopicPackId(Long.parseLong(request.getParameter("topic_pack_id")));
            Topics topicCreated = client.createTopic(topic);
            modelAndView = new ModelAndView("redirect:/topic/"+topicCreated.getTopicId()+"/");
        }catch(Exception ex){
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
            modelAndView = new ModelAndView("redirect:/");
        }
  
        return modelAndView;
    }
}
