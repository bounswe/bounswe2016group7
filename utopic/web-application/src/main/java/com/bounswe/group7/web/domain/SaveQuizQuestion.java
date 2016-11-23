/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

/**
 *
 * @author necil
 */
@JsonDeserialize(as = SaveQuizQuestion.class)
public class SaveQuizQuestion {
    public List <SaveQuizOption> options;
    public String text;
    
    public SaveQuizQuestion(){}
    
    public SaveQuizQuestion(List <SaveQuizOption> options, String text){
        this.text = text;
        this.options = options;
    }
}
