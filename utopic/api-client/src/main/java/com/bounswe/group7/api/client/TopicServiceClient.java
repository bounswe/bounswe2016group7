/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.RatedTopics;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author ugurbor
 */
public class TopicServiceClient extends BaseClient {

    public TopicServiceClient() {
    }

    public TopicServiceClient(String token) {
        super(token);
    }

    public Topics createTopic(Topics request) throws Exception {
        return post(getResource().path("createTopic"), new TypeToken<Topics>() {
        }, request);
    }

    public TopicPacks createTopic(TopicPacks request) throws Exception {
        return post(getResource().path("createTopicPack"), new TypeToken<TopicPacks>() {
        }, request);
    }

    public Topics getTopic(Long topicId) throws Exception {
        return post(getResource().path("getTopic"), new TypeToken<Topics>() {
        }, topicId);
    }

    public List<Topics> getUserTopics(Long userId) throws Exception {
        return post(getResource().path("public/getUserTopics"), new TypeToken<List<Topics>>() {
        }, userId);
    }

    public TopicPacks getTopicPack(Long topicPackId) throws Exception {
        return post(getResource().path("getTopicPack"), new TypeToken<TopicPacks>() {
        }, topicPackId);
    }

    public List<Topics> getRecentTopics() throws Exception {
        return get(getResource().path("public/getRecentTopics"), new TypeToken<List<Topics>>() {
        });
    }

    public List<Topics> getTopTopics() throws Exception {
        return get(getResource().path("public/getTopTopics"), new TypeToken<List<Topics>>() {
        });
    }
    
    public boolean rateTopic(RatedTopics ratedTopic) throws Exception {
        return post(getResource().path("rateTopic"), new TypeToken<Boolean>() {
        }, ratedTopic);
    }

}
