/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.FollowedTopics;
import com.bounswe.group7.model.RatedTopics;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.repository.FollowedTopicsRepository;
import com.bounswe.group7.repository.RatedTopicsRepository;
import com.bounswe.group7.repository.TopicPacksRepository;
import com.bounswe.group7.repository.TopicsRepository;
import java.util.ArrayList;
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
    
    @Autowired
    RatedTopicsRepository ratedTopicsRepository;
    
    @Autowired
    FollowedTopicsRepository followedTopicsRepository;
    
    @Autowired
    TagsService tagsService;

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
        topic.setRateCounter(0);
        if (topic.getTopicPackId() == null) {
            TopicPacks pack = createTopicPack(new TopicPacks(topic.getHeader()));
            pack.setCount(1);
            topic.setTopicPackId(pack.getTopicPackId());
            topic.setOrderBy(1);
        } else {
            TopicPacks pack = topicPacksRepository.findOne(topic.getTopicPackId());
            pack.setCount(pack.getCount() + 1);
            topic.setOrderBy(pack.getCount());
            topicPacksRepository.save(pack);
        }
        for(Tags tag: topic.getTags())
        {
            Tags createdTag = tagsService.createTag(tag);
            tag.setRefCount(createdTag.getRefCount());
            tag.setTagId(createdTag.getTagId());
        }
        return topicsRepository.save(topic);
    }

    public TopicPacks createTopicPack(TopicPacks topicPack) {
        topicPack.setUserId(usersService.getLoggedInUserId());
        topicPack.setCreateDate(new Date());
        return topicPacksRepository.save(topicPack);
    }
    
    public TopicPacks createTopicPackByName(String name)
    {
        return createTopicPack(new TopicPacks(name));
    }
    
    public List<Topics> getUserTopics(Long userId)
    {
        return topicsRepository.findByUserIdOrderByCreateDateDesc(userId);
    }
    
    public List<TopicPacks> getUserTopicPacks(Long userId) {
        return topicPacksRepository.findByUserIdOrderByCreateDateDesc(userId);
    }

    public List<Topics> getRecentTopics(){
        return topicsRepository.findTop10ByOrderByCreateDateDesc();
    }
    
    public List<Topics> getTopTopics() {
        return topicsRepository.findTop10ByOrderByRateDesc();
    }
    
    public boolean rateTopic(RatedTopics ratedTopic)
    {
        RatedTopics temp = ratedTopicsRepository.findByUserIdAndTopicId(
                usersService.getLoggedInUserId(), ratedTopic.getTopicId());
        if(temp != null)
        {
            if(temp.getRate() == ratedTopic.getRate())
                return false;
            Topics topic = topicsRepository.findOne(ratedTopic.getTopicId());
            Double rate = topic.getRate()*topic.getRateCounter();
            rate = rate - temp.getRate() + ratedTopic.getRate();
            topic.setRate(rate/topic.getRateCounter());
            temp.setRate(ratedTopic.getRate());
            topicsRepository.save(topic);
            ratedTopicsRepository.save(temp);
            return true;
        }
        else{
            ratedTopic.setUserId(usersService.getLoggedInUserId());
            Topics topic = topicsRepository.findOne(ratedTopic.getTopicId());
            Double rate = topic.getRate()*topic.getRateCounter();
            rate+=ratedTopic.getRate();
            topic.setRateCounter(topic.getRateCounter()+1);
            topic.setRate(rate/topic.getRateCounter());
            topicsRepository.save(topic);
            ratedTopicsRepository.save(ratedTopic);
            return true;
        }
    }
    
    public List<Topics> getUserFollowedTopics()
    {
        List<FollowedTopics> fList = followedTopicsRepository.findAllByUserId(usersService.getLoggedInUserId());
        List<Topics> followedTopics = new ArrayList<>();
        for(FollowedTopics topic : fList)
            followedTopics.add(topicsRepository.findOne(topic.getTopicId()));
        return followedTopics;
    }
    
    public boolean followTopic(Long topicId)
    {
        FollowedTopics followedTopic = followedTopicsRepository.findByUserIdAndTopicId(usersService.getLoggedInUserId(),topicId);
        if(followedTopic != null) return false;
        followedTopic = new FollowedTopics(usersService.getLoggedInUserId(), topicId);
        followedTopicsRepository.save(followedTopic);
        return true;
    }
    
    public boolean unfollowTopic(Long topicId)
    {
        FollowedTopics followedTopic = followedTopicsRepository.findByUserIdAndTopicId(usersService.getLoggedInUserId(),topicId);
        if(followedTopic != null){
            followedTopicsRepository.delete(followedTopic);
            return true;
        }
        return false;
    }
    
    public boolean checkFollowedTopic(Long topicId)
    {
           FollowedTopics followedTopic = followedTopicsRepository.findByUserIdAndTopicId(usersService.getLoggedInUserId(),topicId);
           if(followedTopic != null) return true;
           else return false;
    }
}
