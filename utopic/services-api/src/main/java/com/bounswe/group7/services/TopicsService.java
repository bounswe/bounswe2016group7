/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.FollowedTopics;
import com.bounswe.group7.model.NextPrevTopic;
import com.bounswe.group7.model.RatedTopics;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.repository.FollowedTopicsRepository;
import com.bounswe.group7.repository.RatedTopicsRepository;
import com.bounswe.group7.repository.TopicPacksRepository;
import com.bounswe.group7.repository.TopicsRepository;
import com.bounswe.group7.repository.UsersRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service class of the Topic Model.
 * @author Yunus Seker
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
    UsersRepository usersRepository;

    @Autowired
    TagsService tagsService;

    /**
     * This method takes a topic id and returns the related topic.
     * @param id id of the topic 
     * @return returns Topic object.
     */
    public Topics getTopic(Long id) {
        return topicsRepository.findOne(id);
    }

    /**
     * This method takes an id of a topic and
     * finds the previous and next topic on the topic pack.
     * OrderBy is used to sort the topic pack.
     * @param id id of the topic.
     * @return returns NextPrevTopic if there is a previous topic it returns it, otherwise null. Same for next.
     */
    public NextPrevTopic getNextPrev(Long id) {
        Topics theTopic = getTopic(id);
        TopicPacks thePack = getTopicPack(theTopic.getTopicPackId());
        NextPrevTopic nextPrev = new NextPrevTopic();
        if (theTopic.getOrderBy() < thePack.getCount()) {
            nextPrev.setNext(topicsRepository.findByTopicPackIdAndOrderBy(theTopic.getTopicPackId(),theTopic.getOrderBy() + 1));
        }
        if (theTopic.getOrderBy() > 1) {
            nextPrev.setPrev(topicsRepository.findByTopicPackIdAndOrderBy(theTopic.getTopicPackId(),theTopic.getOrderBy() - 1));
        }
        
        return nextPrev;
    }
    
    /**
     * This method returns the topic pack
     * @param id id of the topic pack
     * @return returns the Topic pack object.
     */
    public TopicPacks getTopicPack(Long id) {
        return topicPacksRepository.findOne(id);
    }
    
    /**
     * This method returns the topics of a topic pack.
     * @param topicPackId id of the topic pack
     * @return returns a list of the topics of topic pack
     */
    public List<Topics> getTopicsOfTopicPack(Long topicPackId) {
        return topicsRepository.findTopicsOfTopicPack(topicPackId);
    }

    /**
     * this takes a topic object and creates the topic.
     * It sets the userId, rate as zero, rate counter as zero
     * If there is no topic pack given, it is created.
     * @param topic the object that holds the header, material description, tags.
     * @return returns the created topic.
     */
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
        for (Tags tag : topic.getTags()) {
            Tags createdTag = tagsService.getTag(tag);
            tag.setRefCount(createdTag.getRefCount());
            tag.setTagId(createdTag.getTagId());
        }
        return topicsRepository.save(topic);
    }
    
    /**
     * This method creates a topic pack.
     * It gives userId, date.
     * @param topicPack topic pack that holds id.
     * @return returns the created topic pack.
     */
    public TopicPacks createTopicPack(TopicPacks topicPack) {
        topicPack.setUserId(usersService.getLoggedInUserId());
        topicPack.setCreateDate(new Date());
        return topicPacksRepository.save(topicPack);
    }
    
    /**
     * This method creates a topic pack by name
     * @param name name of the topic pack
     * @return returns the created topic pack.
     */
    public TopicPacks createTopicPackByName(String name) {
        return createTopicPack(new TopicPacks(name));
    }

    /**
     * This method returns the topics of a creator
     * @param userId creator id
     * @return returns the topic list of the user.
     */
    public List<Topics> getUserTopics(Long userId) {
        return topicsRepository.findByUserIdOrderByCreateDateDesc(userId);
    }
    
    /**
     * This method returns the topic packs of the creator
     * @param userId creator id
     * @return returns the topic pack list of the user.
     */
    public List<TopicPacks> getUserTopicPacks(Long userId) {
        return topicPacksRepository.findByUserIdOrderByCreateDateDesc(userId);
    }
    
    /**
     * This method returns the most 10 recent topics.
     * @return returns the recent topic list
     */
    public List<Topics> getRecentTopics() {
        return topicsRepository.findTop10ByOrderByCreateDateDesc();
    }

    /**
     * This method returns the most rated 10 topics.
     * @return returns the most rated topic list
     */
    public List<Topics> getTopTopics() {
        return topicsRepository.findTop10ByOrderByRateDesc();
    }

    /**
     * This method takes a RatedTopics object and rates the topic.
     * If the user never rated, it is added to calculation.
     * If the user rated before and vote changed, previous vote
     * is taken back and calculations made for the new vote.
     * If the user rated before and the vote is the same, fails
     * @param ratedTopic the object that holds topic id and vote.
     * @return returns true if succeed. false otherwise.
     */
    public boolean rateTopic(RatedTopics ratedTopic) {
        RatedTopics temp = ratedTopicsRepository.findByUserIdAndTopicId(
                usersService.getLoggedInUserId(), ratedTopic.getTopicId());
        if (temp != null) {
            if (temp.getRate() == ratedTopic.getRate()) {
                return false;
            }
            Topics topic = topicsRepository.findOne(ratedTopic.getTopicId());
            Double rate = topic.getRate() * topic.getRateCounter();
            rate = rate - temp.getRate() + ratedTopic.getRate();
            topic.setRate(rate / topic.getRateCounter());
            temp.setRate(ratedTopic.getRate());
            topicsRepository.save(topic);
            ratedTopicsRepository.save(temp);
            return true;
        } else {
            ratedTopic.setUserId(usersService.getLoggedInUserId());
            Topics topic = topicsRepository.findOne(ratedTopic.getTopicId());
            Double rate = topic.getRate() * topic.getRateCounter();
            rate += ratedTopic.getRate();
            topic.setRateCounter(topic.getRateCounter() + 1);
            topic.setRate(rate / topic.getRateCounter());
            topicsRepository.save(topic);
            ratedTopicsRepository.save(ratedTopic);
            return true;
        }
    }
    
    /**
     * this method returns the followed topics of the user
     * @return returns the list of topics
     */
    public List<Topics> getUserFollowedTopics() {
        return topicsRepository.findAllByUserId(usersService.getLoggedInUserId());
    }
    
    /**
     * this method returns the users that followed a topic.
     * @param topicId the topic id
     * @return returns the users list that following the topic
     */
    public List<Users> getTopicFollowers(Long topicId) {
        return usersRepository.findUsersByTopicId(topicId);
    }

    /**
     * This method follows a topic if it is un-followed, un-follows it if it followed.
     * @param topicId the topic to be followed
     * @return returns true if it can follow, false otherwise.
     */
    public boolean followTopic(Long topicId) {
        if (checkFollowedTopic(topicId)) {
            return unfollowTopic(topicId);
        } else {
            FollowedTopics followedTopic = new FollowedTopics(usersService.getLoggedInUserId(), topicId);
            followedTopicsRepository.save(followedTopic);
            return true;
        }
    }
    
    /**
     * This method un-follows a topic
     * @param topicId the topic id to be followed
     * @return returns true if can be un-followed
     */
    public boolean unfollowTopic(Long topicId) {
        FollowedTopics followedTopic = followedTopicsRepository.findByUserIdAndTopicId(usersService.getLoggedInUserId(), topicId);
        if (followedTopic != null) {
            followedTopicsRepository.delete(followedTopic);
            return true;
        }
        return false;
    }
    
    /**
     * This method returns that a topic is followed or not.
     * @param topicId the topic to be checked
     * @return returns true if followed, false if un followed.
     */
    public boolean checkFollowedTopic(Long topicId) {
        FollowedTopics followedTopic = followedTopicsRepository.findByUserIdAndTopicId(usersService.getLoggedInUserId(), topicId);
        if (followedTopic != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method returns the rate of the topic
     * @param topicId the topic id
     * @return returns the rate of the topic. Between 1-5.
     */
    public double getTopicRate(Long topicId) {
        return topicsRepository.findOne(topicId).getRate();
    }
    
    /**
     * This method returns the rate of the user on a topic.
     * @param topicId the topic
     * @return returns the rate of the user to the topic. between 1-5.
     */
    public int getTopicUserRate(Long topicId)
    {
        RatedTopics result = ratedTopicsRepository.findByUserIdAndTopicId(usersService.getLoggedInUserId(), topicId);
        if(result != null) 
            return result.getRate();
        return 0;
        
    }
}
