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
public class QuestionAnswers {
    public List<QuestionAndAnswer> questionList;
    public Long quizId;
    public Long topicId;

    public QuestionAnswers() {
    }

    public QuestionAnswers(List<QuestionAndAnswer> questionList, Long quizId, Long topicId) {
        this.questionList = questionList;
        this.quizId = quizId;
        this.topicId = topicId;
    }
    
}
