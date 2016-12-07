/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import java.util.List;

/**
 *
 * @author Batuhan
 */
public class Quiz {
    public List<SaveQuizQuestion> questionList;
    public Long quizId;

    public Quiz() {
    }

    public Quiz(List<SaveQuizQuestion> questionList, Long quizId) {
        this.questionList = questionList;
        this.quizId = quizId;
    }
    
}
