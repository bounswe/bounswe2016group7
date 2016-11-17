/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.repository.TopicPacksRepository;
import com.bounswe.group7.repository.TopicsRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    TopicPacksRepository topicPacksRepository;

    @Autowired
    UsersService usersService;

    public Topics getTopic(Long id) {
        return topicsRepository.findOne(id);
    }

    public TopicPacks getTopicPack(Long id) {
        return topicPacksRepository.findOne(id);
    }

    public Topics createTopic(Topics topic) {
        topic.setUserId(usersService.getLoggedInUserId());
        topic.setCreateDate(new Date());
        topic.setRate(0.00);
        if (topic.getTopicPackId() == null) {
            TopicPacks pack = createTopicPack(new TopicPacks(topic.getHeader()));
            topic.setTopicPackId(pack.getTopicPackId());
            topic.setOrderBy(1);
        } else {
            TopicPacks pack = topicPacksRepository.findOne(topic.getTopicPackId());
            pack.setCount(pack.getCount() + 1);
            topic.setOrderBy(pack.getCount());
            topicPacksRepository.save(pack);
        }
        return topicsRepository.save(topic);
    }

    public TopicPacks createTopicPack(TopicPacks topicPack) {
        topicPack.setUserId(usersService.getLoggedInUserId());
        topicPack.setCreateDate(new Date());
        return topicPacksRepository.save(topicPack);
    }
    
    public List<Topics> getUserTopics(Long userId)
    {
        return topicsRepository.findByUserId(userId);
    }
    
    public List<Topics> getRecentTopics(){
        return topicsRepository.findTop10ByOrderByCreateDateDesc();
    }
    
    public List<Topics> getTopTopics() {
        return topicsRepository.fintTop10ByOrderByRateDesc();
    }
}
