/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Users;
import com.bounswe.group7.repository.UsersRepository;
import com.bounswe.group7.security.JwtTokenUtil;
import com.bounswe.group7.security.JwtUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ugurbor
 */
@Service
public class UsersService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public Users updateUser(Users user){
        return usersRepository.save(user);
    }

    public Users getLoggedInUser() {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Users user = usersRepository.findByUsername(username);
        return user;
    }

    public Long getLoggedInUserId() {
        return getLoggedInUser().getId();
    }

    public Users getUser(Long userId) {
        return usersRepository.findOne(userId);
    }

    public String changePassword(Users changePasswordReq) {
        Users user = usersRepository.findOne(getLoggedInUserId());
        if (passwordEncoder().matches(changePasswordReq.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder().encode(changePasswordReq.getNewPassReq()));
            usersRepository.save(user);
            return "password changed";
        }

        return "not changed";
    }
}
