/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * @author necil
 */
@JsonDeserialize(as = SaveQuizOption.class)
public class SaveQuizOption {
    public String text;
    public boolean isCorrect;
    
    public SaveQuizOption(){}
    
    public SaveQuizOption(String text, boolean isCorrect){
        this.text = text;
        this.isCorrect = isCorrect;
    }
}
