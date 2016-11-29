/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Topics;
import com.bounswe.group7.repository.TopicsRepository;
import io.jsonwebtoken.lang.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ugurbor
 */
@Service
public class SearchService {
    
    @Autowired
    private TopicsRepository topicsRepository;
    
    public List<Topics> searchTopics(String keywords){
        String[] keywordArr = keywords.split(" ");
        List<String> keywordList = Collections.arrayToList(keywordArr);
        
        ConcurrentHashMap<Long, Topics> searchResults = new ConcurrentHashMap<>();
        ConcurrentHashMap<Long, Integer> idWithFreq = new ConcurrentHashMap<>();
        
        keywordList.parallelStream().forEach((key)-> {
            topicsRepository.findByIgnoreCaseHeaderContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 5);
            });
        });
        
        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseDescriptionContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getDescription(), key) * 2);
            });
        });
        
        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseContentContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getContent(), key));
            });
        });

        List<Topics> res = new ArrayList<Topics>(searchResults.values());        
        return res ;
    }
    
}
