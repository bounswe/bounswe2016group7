/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Topics;
import com.bounswe.group7.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author myunu
 */
@Service
public class TopicsService {
    @Autowired
    TopicsRepository topicsRepository;
    
    @Autowired
    UsersService usersService;
    
    public Topics createTopic(Topics topic){
        topic.setUserId(usersService.getLoggedInUserId());
        return topicsRepository.save(topic);
    }
}
