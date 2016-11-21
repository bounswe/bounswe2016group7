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
    List<QuestionAndAnswer> questionList;

    public QuestionAnswers(List<QuestionAndAnswer> questionList) {
        this.questionList = questionList;
    }
}
