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
public class QuestionAndAnswer {
    public Long questionId;
    public Long optionId;

    public QuestionAndAnswer() {
    }

    public QuestionAndAnswer(Long questionId, Long optionId) {
        this.questionId = questionId;
        this.optionId = optionId;
    }
}
