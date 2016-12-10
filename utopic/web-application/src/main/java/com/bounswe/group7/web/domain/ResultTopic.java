/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import com.bounswe.group7.model.Tags;
import java.util.List;

/**
 *
 * @author Batuhan
 */
public class ResultTopic {
    public Long id;
    public String header;
    public String description;
    public List<Tags> tags;
    public int commentNumber;
    public double rate;

    public ResultTopic() {
    }

    public ResultTopic(Long id, String header, String description, List<Tags> tags, int commentNumber, double rate) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.tags = tags;
        this.commentNumber = commentNumber;
        this.rate = rate;
    }
    
    
    
}
