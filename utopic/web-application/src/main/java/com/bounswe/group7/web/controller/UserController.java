package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.ReviewServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Reviews;
import com.bounswe.group7.model.Topics;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.web.domain.ProfileReview;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Batuhan
 */
@RestController
public class UserController {
    
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        UserServiceClient userClient = new UserServiceClient((String) request.getSession().getAttribute("token"));
        HttpSession session = request.getSession();
        try{
            Users user = new Users(session.getAttribute("username").toString(), request.getParameter("password"));
            user.setNewPassReq(request.getParameter("newPassword"));
            userClient.changePassword(user);
        }catch(Exception ex){
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }
    
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView showProfile(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("profile");
        UserServiceClient userClient = new UserServiceClient((String) request.getSession().getAttribute("token"));
        TopicServiceClient topicClient = new TopicServiceClient((String) request.getSession().getAttribute("token"));
        ReviewServiceClient reviewClient = new ReviewServiceClient((String) request.getSession().getAttribute("token"));
        try{
            Users profiledUser = userClient.getUser(id);
            List<Topics> topicList = topicClient.getUserTopics(id);
            List<Reviews> reviewList = reviewClient.getUserReviews(id);
            List<ProfileReview> profileReviewList = new ArrayList<ProfileReview>();
            ObjectMapper mapper = new ObjectMapper();
            for(Reviews temp: reviewList){
                String username = userClient.getUser(temp.getReviewerId()).getUsername();
                profileReviewList.add(new ProfileReview(temp.getReviewId(), temp.getReviewerId(), username, temp.getDateCreated(), temp.getText()));
            }
            String profileReviews = mapper.writeValueAsString(profileReviewList);
            String listOfTopics = mapper.writeValueAsString(topicList);
            modelAndView.addObject("profiledUser", profiledUser);
            modelAndView.addObject("topics", listOfTopics);
            modelAndView.addObject("reviews", profileReviews);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/setbio", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String setBio(HttpServletRequest request, @RequestBody String bio){
        UserServiceClient userClient = new UserServiceClient((String) request.getSession().getAttribute("token"));
        try{
            userClient.updateUserBio(bio);
            return bio;
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
    
    @RequestMapping(value = "/setassociation", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        UserServiceClient userClient = new UserServiceClient((String) request.getSession().getAttribute("token"));
        try{
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
}
