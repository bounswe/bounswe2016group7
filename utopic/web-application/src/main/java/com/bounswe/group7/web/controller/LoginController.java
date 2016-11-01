/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ugurbor
 */
@Controller
public class LoginController {

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("uname");
        String pwd = request.getParameter("pass");
        LoginServiceClient client = new LoginServiceClient();
        Users token = null;
        try {
            token = client.login(new Users(username, pwd));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (token == null ? "" : token.getToken());
    }
}
