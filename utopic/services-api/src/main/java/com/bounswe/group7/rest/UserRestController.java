package com.bounswe.group7.rest;

import com.bounswe.group7.model.Users;
import com.bounswe.group7.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bounswe.group7.security.JwtTokenUtil;
import com.bounswe.group7.security.JwtUser;
import com.bounswe.group7.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class UserRestController {
    
    @Autowired
    private UsersService usersService;
    
    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
    @RequestMapping(value = "getLoggedInUser", method = RequestMethod.GET)
    @ResponseBody
    public Users getLoggedInUser(HttpServletRequest request) {
        return usersService.getLoggedInUser();
    }
    
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('EXPLORER', 'CREATOR', 'ADMIN')")
    public String changePassword(@RequestBody Users changePasswordReq) {
        return usersService.changePassword(changePasswordReq);
    }
    
    @RequestMapping(value = "public/getUser", method = RequestMethod.POST)
    public Users getUser(@RequestBody Long userId) {
        return usersService.getUser(userId);
    }
    
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public Users getUser(@RequestBody Users theUser) {
        return usersService.updateUser(theUser);
    }
}
