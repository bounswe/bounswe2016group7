/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
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

    @RequestMapping(path = "/getTopic", method = RequestMethod.POST)
    @ResponseBody
    public Topics getTopic(@RequestBody Long topicId) {
        return topicsService.getTopic(topicId);
    }

    @RequestMapping(path = "/getTopicPack", method = RequestMethod.POST)
    @ResponseBody
    public TopicPacks getTopicPack(@RequestBody Long topicPackId) {
        return topicsService.getTopicPack(topicPackId);
    }

    @RequestMapping(path = "/getRecentTopics", method = RequestMethod.GET)
    @ResponseBody
    public List<Topics> getRecentTopics() {
        return topicsService.getRecentTopics();
    }

}
