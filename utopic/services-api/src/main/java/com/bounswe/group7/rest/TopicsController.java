/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.FollowedTopics;
import com.bounswe.group7.model.NextPrevTopic;
import com.bounswe.group7.model.RatedTopics;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.services.TopicsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author myunu
 */
@RestController
public class TopicsController {

    @Autowired
    TopicsService topicsService;

    @RequestMapping(path = "/createTopic", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Topics createTopic(@RequestBody Topics topic) {
        return topicsService.createTopic(topic);
    }

    @RequestMapping(path = "/createTopicPack", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public TopicPacks createTopicPack(@RequestBody TopicPacks topicPack) {
        return topicsService.createTopicPack(topicPack);
    }

    @RequestMapping(path = "/createTopicPackByName", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public TopicPacks createTopicPackByName(@RequestBody String name) {
        return topicsService.createTopicPackByName(name);
    }

    @RequestMapping(path = "getTopic", method = RequestMethod.POST)
    @ResponseBody
    public Topics getTopic(@RequestBody Long topicId) {
        return topicsService.getTopic(topicId);
    }

    @RequestMapping(path = "getNextPrev", method = RequestMethod.POST)
    @ResponseBody
    public NextPrevTopic getNextPrev(@RequestBody Long topicId) {
        return topicsService.getNextPrev(topicId);
    }
    
    @RequestMapping(path = "getTopicPack", method = RequestMethod.POST)
    @ResponseBody
    public TopicPacks getTopicPack(@RequestBody Long topicPackId) {
        return topicsService.getTopicPack(topicPackId);
    }
    
    

    @RequestMapping(path = "getTopicsOfTopicPack", method = RequestMethod.POST)
    @ResponseBody
    public List<Topics> getTopicsOfTopicPack(@RequestBody Long topicPackId) {
        return topicsService.getTopicsOfTopicPack(topicPackId);
    }

    @RequestMapping(path = "public/getRecentTopics", method = RequestMethod.GET)
    @ResponseBody
    public List<Topics> getRecentTopics() {
        return topicsService.getRecentTopics();
    }

    @RequestMapping(path = "public/getTopTopics", method = RequestMethod.GET)
    @ResponseBody
    public List<Topics> getTopTopics() {
        return topicsService.getTopTopics();
    }

    @RequestMapping(path = "public/getUserTopics", method = RequestMethod.POST)
    @ResponseBody
    public List<Topics> getUserTopics(@RequestBody Long userId) {
        return topicsService.getUserTopics(userId);
    }

    @RequestMapping(path = "getUserTopicPacks", method = RequestMethod.POST)
    @ResponseBody
    public List<TopicPacks> getUserTopicPacks(@RequestBody Long userId) {
        return topicsService.getUserTopicPacks(userId);
    }

    @RequestMapping(path = "rateTopic", method = RequestMethod.POST)
    @ResponseBody
    public boolean rateTopic(@RequestBody RatedTopics ratedTopic) {
        return topicsService.rateTopic(ratedTopic);
    }

    @RequestMapping(path = "getUserFollowedTopics", method = RequestMethod.GET)
    @ResponseBody
    public List<Topics> getUserFollowedTopics() {
        return topicsService.getUserFollowedTopics();
    }

    @RequestMapping(path = "getTopicFollowers", method = RequestMethod.POST)
    @ResponseBody
    public List<Users> getTopicFollowers(@RequestBody Long topicId) {
        return topicsService.getTopicFollowers(topicId);
    }

    @RequestMapping(path = "followTopic", method = RequestMethod.POST)
    @ResponseBody
    public boolean followTopic(@RequestBody Long topicId) {
        return topicsService.followTopic(topicId);
    }

    @RequestMapping(path = "unfollowTopic", method = RequestMethod.POST)
    @ResponseBody
    public boolean unfollowTopic(@RequestBody Long topicId) {
        return topicsService.unfollowTopic(topicId);
    }

    @RequestMapping(path = "checkFollowedTopic", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkFollowedTopic(@RequestBody Long topicId) {
        return topicsService.checkFollowedTopic(topicId);
    }

    @RequestMapping(path = "getTopicRate", method = RequestMethod.POST)
    @ResponseBody
    public double getTopicRate(@RequestBody Long topicId) {
        return topicsService.getTopicRate(topicId);
    }

}
