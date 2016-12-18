/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ugurbor
 */
public class QuizProgress {

    private TopicPacks topicPack;
    
    private Double totalProgress;

    public TopicPacks getTopicPack() {
        return topicPack;
    }

    public void setTopicPack(TopicPacks topicPack) {
        this.topicPack = topicPack;
    }


    public Double getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(Double totalProgress) {
        this.totalProgress = totalProgress;
    }


}
