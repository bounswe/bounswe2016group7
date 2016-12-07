/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

/**
 *
 * @author Batuhan
 */
public class UserTopicPack {
    public String topicPackName;
    public Long topicPackId;
    
    public UserTopicPack(){}
    
    public UserTopicPack(String topicPackName, Long topicPackId) {
        this.topicPackName = topicPackName;
        this.topicPackId = topicPackId;
    }
    
}
