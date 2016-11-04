/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.model.security.Authority;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ugurbor
 */
@RestController
public class MainController {
    
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView index = new ModelAndView("index");
        return index;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response)  throws NullPointerException{
        Users user = new Users();
        LoginServiceClient client = new LoginServiceClient();
        ModelAndView modelAndView = new ModelAndView("index");
        Authority userAuthority = new Authority();
        try {
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setGender(request.getParameter("gender"));
            String status = request.getParameter("status");
            if(status.equals("explorer"))
                userAuthority.setId((long)1);
            else
                userAuthority.setId((long)2);
            List <Authority> authList = new ArrayList<Authority>();
            authList.add(userAuthority);
            user.setAuthorities(authList);
            client.register(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }
}
