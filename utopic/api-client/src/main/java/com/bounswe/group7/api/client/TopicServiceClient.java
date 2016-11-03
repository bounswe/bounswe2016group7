/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author ugurbor
 */
public class TopicServiceClient extends BaseClient {

    public TopicServiceClient() {
    }

    public Topics createTopic(Topics request) throws Exception {
        return post(getResource().path("createTopic"), new TypeReference<Topics>() {
        }, request);
    }

    public TopicPacks createTopic(TopicPacks request) throws Exception {
        return post(getResource().path("createTopicPack"), new TypeReference<TopicPacks>() {
        }, request);
    }
}
