/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 *
 * @author myunu
 */
@Entity
public class SolvedQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLVEDQUESTIONS_SEQ")
    @SequenceGenerator(name = "SOLVEDQUESTIONS_SEQ", sequenceName = "SOLVEDQUESTIONS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "solved_questions_id", nullable = false)
    private Long solvedQuestionsId;
    
    @Column(name = "solved_quiz_id", nullable = false)
    private Long solvedQuizId;
    
    @Column(name = "question_id", nullable = false)
    private Long questionId;
    
    @Column(name = "right_answer", nullable = false)
    private char rightAnswer;
    
    @Column(name = "chosen_answer", nullable = false)
    private char chosenAnswer;
    
    @Column(name = "true_or_false", nullable = false)
    private boolean trueOrFalse;

    public SolvedQuestions() {
    }

    public SolvedQuestions(Long solvedQuizId, Long questionId, char rightAnswer, char chosenAnswer, boolean trueOrFalse) {
        this.solvedQuizId = solvedQuizId;
        this.questionId = questionId;
        this.rightAnswer = rightAnswer;
        this.chosenAnswer = chosenAnswer;
        this.trueOrFalse = trueOrFalse;
    }

    public Long getSolvedQuestionsId() {
        return solvedQuestionsId;
    }

    public void setSolvedQuestionsId(Long solvedQuestionsId) {
        this.solvedQuestionsId = solvedQuestionsId;
    }

    public Long getSolvedQuizId() {
        return solvedQuizId;
    }

    public void setSolvedQuizId(Long solvedQuizId) {
        this.solvedQuizId = solvedQuizId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public char getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(char rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public char getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(char chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }
    
    
    
}
