/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.Topics;
import com.bounswe.group7.services.SearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ugurbor
 */
@RestController
public class SearchController {
    @Autowired
    SearchService searchService;
    
    @RequestMapping(path = "public/searchTopics", method = RequestMethod.POST)
    public List<Topics> searchTopics(@RequestBody String keywords){
     return searchService.searchTopics(keywords);
    }
    
    @RequestMapping(path = "recommendedTopics", method = RequestMethod.POST)
    public List<Topics> searchTopics(@RequestBody Long topicId) {
        return searchService.recommendedTopics(topicId);
    }
}
