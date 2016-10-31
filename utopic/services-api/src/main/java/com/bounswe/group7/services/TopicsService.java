/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.security.Topics;
import com.bounswe.group7.repository.TopicsRepository;
import com.bounswe.group7.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author myunu
 */
@Service
public class TopicsService {
    @Autowired
    TopicsRepository topicsRepository;
            
    public Topics createTopic(Topics topic){
        return topicsRepository.save(topic);
    }
}
