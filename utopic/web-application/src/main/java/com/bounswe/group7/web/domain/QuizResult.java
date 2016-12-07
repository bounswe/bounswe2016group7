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
public class QuizResult {
    public int correctAnswerNumber;
    public int wrongAnswerNumber;
    public List<QuestionAndAnswer> wrongAnswers;

    public QuizResult(int correctAnswerNumber, int wrongAnswerNumber, List<QuestionAndAnswer> wrongAnswers) {
        this.correctAnswerNumber = correctAnswerNumber;
        this.wrongAnswerNumber = wrongAnswerNumber;
        this.wrongAnswers = wrongAnswers;
    }
}
