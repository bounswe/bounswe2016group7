/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ugurbor
 */

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginServiceClient client = new LoginServiceClient();
        Users user = null;
        try {
            user = client.login(new Users(username, password));
            HttpSession session = request.getSession();
            session.setAttribute("token", user.getToken());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("user_id", user.getId());
            ModelAndView prevPage = new ModelAndView("redirect:/");
            return prevPage;
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            ModelAndView index = new ModelAndView("redirect:/");
            return index;
        }
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        ModelAndView index = new ModelAndView("redirect:/");
        return index;
    }
}
