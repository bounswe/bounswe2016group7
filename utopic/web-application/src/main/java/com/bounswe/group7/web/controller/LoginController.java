package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles login and logout operations. Saving data to the session and erasing
 * them is done in this rest controller class.
 * 
 * @author Batuhan
 */

@RestController
public class LoginController {
    /**
     * Username and password is entered by the guest. By using, client method
     * if this user exists in the system, the user logs in. User, id, token, 
     * username and authorities of the user are saved into session. After this,
     * user is redirected to the home page.
     * 
     * @param request -> username and password
     * @return home_page or landing_page
     * @throws IOException 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginServiceClient client = new LoginServiceClient();
        Users user = null;
        ModelAndView index;
        try {
            user = client.login(new Users(username, password));
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("token", user.getToken());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("authorities", user.getAuthorities());
            index = new ModelAndView("redirect:/home");
        } catch (Exception ex) {
            ex.printStackTrace();
            index = new ModelAndView("redirect:/");
        }
        return index;
    }
    /**
     * When the user wants to logout, automatically sends a request. All data
     * saved to the session, is removed.
     * 
     * @param request
     * @return landing_page
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("token");
        session.removeAttribute("username");
        session.removeAttribute("authorities");
        ModelAndView index = new ModelAndView("redirect:/");
        return index;
    }
}
