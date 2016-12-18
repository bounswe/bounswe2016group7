/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.api.client.SearchServiceClient;
import com.bounswe.group7.api.client.TagServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.model.security.Authority;
import com.bounswe.group7.model.security.AuthorityName;
import com.bounswe.group7.web.domain.TopicWithStatistics;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        ModelAndView index = new ModelAndView("index");
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        TagServiceClient tagClient = new TagServiceClient((String) session.getAttribute("token"));
        try {
            List<Topics> recentTopicsList = client.getRecentTopics();
            List<Topics> topTopicsList = client.getTopTopics();
            List<TopicWithStatistics> recentTopicStatisticsList = new ArrayList<TopicWithStatistics>();
            for (Topics temp : recentTopicsList) {
                Long topicId = temp.getTopicId();
                int commentNumber = commentClient.getTopicComments(topicId).size();
                recentTopicStatisticsList.add(new TopicWithStatistics(temp.getTopicId(), temp.getHeader(), commentNumber, temp.getRate()));
            }
            List<TopicWithStatistics> topTopicStatisticsList = new ArrayList<TopicWithStatistics>();
            for (Topics temp : topTopicsList) {
                Long topicId = temp.getTopicId();
                int commentNumber = commentClient.getTopicComments(topicId).size();
                topTopicStatisticsList.add(new TopicWithStatistics(temp.getTopicId(), temp.getHeader(), commentNumber, temp.getRate()));
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Tags> categoryList = tagClient.getCategoryTags();
            String recentTopics = mapper.writeValueAsString(recentTopicStatisticsList);
            String topTopics = mapper.writeValueAsString(topTopicStatisticsList);
            String categories = mapper.writeValueAsString(categoryList);
            index.addObject("recentTopics", recentTopics);
            index.addObject("topTopics", topTopics);
            index.addObject("categories", categories);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addFlashAttribute("error", ex.getMessage());
        }
        return index;
    }

    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        ModelAndView index = new ModelAndView("home");
        HttpSession session = request.getSession();
        TopicServiceClient client = new TopicServiceClient((String) session.getAttribute("token"));
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        SearchServiceClient searchClient = new SearchServiceClient((String) session.getAttribute("token"));
        TagServiceClient tagClient = new TagServiceClient((String) session.getAttribute("token"));
        try {
            List<Topics> recentTopicsList = client.getRecentTopics();
            List<Topics> topTopicsList = client.getTopTopics();
            List<Topics> recommendedTopicsList = searchClient.userRecommendations();
            List<TopicWithStatistics> recentTopicStatisticsList = new ArrayList<TopicWithStatistics>();
            for (Topics temp : recentTopicsList) {
                Long topicId = temp.getTopicId();
                int commentNumber = commentClient.getTopicComments(topicId).size();
                recentTopicStatisticsList.add(new TopicWithStatistics(temp.getTopicId(), temp.getHeader(), commentNumber, temp.getRate()));
            }
            List<TopicWithStatistics> topTopicStatisticsList = new ArrayList<TopicWithStatistics>();
            for (Topics temp : topTopicsList) {
                Long topicId = temp.getTopicId();
                int commentNumber = commentClient.getTopicComments(topicId).size();
                topTopicStatisticsList.add(new TopicWithStatistics(temp.getTopicId(), temp.getHeader(), commentNumber, temp.getRate()));
            }
            List<TopicWithStatistics> recommendedTopicStatisticsList = new ArrayList<TopicWithStatistics>();
            for (Topics temp : recommendedTopicsList) {
                Long topicId = temp.getTopicId();
                int commentNumber = commentClient.getTopicComments(topicId).size();
                recommendedTopicStatisticsList.add(new TopicWithStatistics(temp.getTopicId(), temp.getHeader(), commentNumber, temp.getRate()));
            }
            List<Tags> categoryList = tagClient.getCategoryTags();
            List<Topics>  interestedTopicsList = client.getUserFollowedTopics();
            ObjectMapper mapper = new ObjectMapper();
            String recentTopics = mapper.writeValueAsString(recentTopicStatisticsList);
            String topTopics = mapper.writeValueAsString(topTopicStatisticsList);
            String interestedTopics = mapper.writeValueAsString(interestedTopicsList);
            String recommendedTopics = mapper.writeValueAsString(recommendedTopicsList);
            String categories = mapper.writeValueAsString(categoryList);
            index.addObject("recentTopics", recentTopics);
            index.addObject("topTopics", topTopics);
            index.addObject("interestedTopics", interestedTopics);
            index.addObject("recommendedTopics", recommendedTopics);
            index.addObject("categories", categories);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addFlashAttribute("error", ex.getMessage());
        }
        return index;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, RedirectAttributes attributes) throws NullPointerException {
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
            String level = request.getParameter("status");
            List<Authority> authorityList = new ArrayList<Authority>();
            if (level.equals("creator")) {
                authorityList.add(new Authority(new Long(AuthorityName.ROLE_CREATOR.getId()), AuthorityName.ROLE_CREATOR));
                authorityList.add(new Authority(new Long(AuthorityName.ROLE_EXPLORER.getId()), AuthorityName.ROLE_EXPLORER));

            } else if (level.equals("explorer")) {
                authorityList.add(new Authority(new Long(AuthorityName.ROLE_EXPLORER.getId()), AuthorityName.ROLE_EXPLORER));
            }
            user.setAuthorities(authorityList);
            client.register(user);
            //TODO mailing is needed after the registration
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addFlashAttribute("error", ex.getMessage());
        }
        return modelAndView;
    }
}
