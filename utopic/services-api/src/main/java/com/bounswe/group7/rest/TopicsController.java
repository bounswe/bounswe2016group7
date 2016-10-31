/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.security.Topics;
import com.bounswe.group7.services.TopicsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Topics createTopic(@RequestBody Topics topic) {
        return topicsService.createTopic(topic);
    }
}
