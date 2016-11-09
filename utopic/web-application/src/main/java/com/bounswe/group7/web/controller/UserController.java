package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.UserServiceClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bounswe.group7.model.Users;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        UserServiceClient userClient = new UserServiceClient();
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
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("profile");
        // TODO client for getting profile page contents should be implemented
        return modelAndView;
    }
}
