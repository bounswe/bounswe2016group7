/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Batuhan
 */
public class QuizResult {
    public int correctAnswerNumber;
    public int wrongAnswerNumber;
    public List<SaveQuizQuestion> results;

    public QuizResult() {
        results = new ArrayList();
    }

    public QuizResult(int correctAnswerNumber, int wrongAnswerNumber, List<SaveQuizQuestion> results) {
        this.correctAnswerNumber = correctAnswerNumber;
        this.wrongAnswerNumber = wrongAnswerNumber;
        this.results = results;
    }
}
