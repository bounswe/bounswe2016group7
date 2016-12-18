/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.Topics;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author ugurbor
 */
public class SearchServiceClient extends BaseClient {

    public SearchServiceClient() {
    }

    public SearchServiceClient(String token) {
        super(token);
    }

    public List<Topics> searchTopics(String keywords) throws Exception {
        return post(getResource().path("public/searchTopics"), new TypeToken<List<Topics>>() {
        }, keywords);
    }

    public List<Topics> topicRecommendations(Long topicId) throws Exception {
        return post(getResource().path("topicRecommendations"), new TypeToken<List<Topics>>() {
        }, topicId);
    }
    
    public List<Topics> userRecommendations() throws Exception {
        return get(getResource().path("topicRecommendations"), new TypeToken<List<Topics>>() {
        });
    }
}
