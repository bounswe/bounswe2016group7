/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author myunu
 */
@Entity
@Table(name = "QUESTIONS")
public class Questions implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ")
    @SequenceGenerator(name = "QUESTION_SEQ", sequenceName = "QUESTION_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "question_id", nullable = false)
    private Long questionId;
    
    @Column(name = "quiz_id", nullable = false)
    private Long quizId;
    
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;
    
    private String question;
    
    private String choiceA;
    
    private String choiceB;
    
    private String choiceC;
    
    private String choiceD;
    
    @Column(name = "right_answer", nullable = false)
    private char rightAnswer;
    
    @Transient
    private char chosenAnswer;

    public Questions() {
        choiceA = "Default_Choice";
        choiceB = "Default_Choice";
        choiceC = "Default_Choice";
        choiceD = "Default_Choice";
    }

    public char getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(char chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
    
    
    
    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public char getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(char rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
    public int compareTo(Questions q){
        return (int) (this.questionId - q.getQuestionId());
    }
    
    public int compare(Questions a, Questions b){
        return (int) (a.getQuestionId() - b.getQuestionId());
    }
}
