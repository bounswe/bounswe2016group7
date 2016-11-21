/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ugurbor
 */
@RestController
public class MainController {
    
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView index = new ModelAndView("index");
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        try {
            List<Topics> recentTopicsList = client.getRecentTopics();
            ObjectMapper mapper = new ObjectMapper();
            String recentTopics = mapper.writeValueAsString(recentTopicsList);
            index.addObject("recentTopics", recentTopics);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addFlashAttribute("error", ex.getMessage());
        }
        return index;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, RedirectAttributes attributes)  throws NullPointerException{
        Users user = new Users();
        LoginServiceClient client = new LoginServiceClient();
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        try {
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setGender(request.getParameter("gender"));
            client.register(user);
            //TODO mailing is needed after the registration
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addFlashAttribute("error", ex.getMessage());
        }
        return modelAndView;
    }
}
