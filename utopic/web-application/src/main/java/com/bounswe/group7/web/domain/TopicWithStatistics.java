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
public class TopicWithStatistics {
    public Long topic_id;
    public String topic_name;
    public int commentNumber;
    public Double rate;

    public TopicWithStatistics(Long topic_id, String topic_name, int commentNumber, Double rate) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
        this.commentNumber = commentNumber;
        this.rate = rate;
    }
}
