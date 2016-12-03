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
 * @author necil
 */
public class SaveTopic {
    public String content;
    public String description;
    public String header;
    public List <Tags> tags;
    public List <SaveQuizQuestion> questions;
    
    public SaveTopic(){}
    
    public SaveTopic(String content, String description, String header, List <Tags> tags, List <SaveQuizQuestion> questions){
        this.content = content;
        this.description = description;
        this.header = header;
        this.tags = tags;
        this.questions = questions;
    }
}
