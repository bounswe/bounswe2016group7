/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 *
 * @author myunu
 */
@Entity
public class SolvedQuizes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLVEDQUIZES_SEQ")
    @SequenceGenerator(name = "SOLVEDQUIZES_SEQ", sequenceName = "SOLVEDQUIZES_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "solved_quiz_id", nullable = false)
    private Long solvedQuizId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "quiz_id", nullable = false)
    private Long quizId;
    
    private Double score;
    
    private Date solveDate;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "solved_quiz_id", referencedColumnName = "solved_quiz_id"),})
    private List<SolvedQuestions> solvedQuestions;

    public List<SolvedQuestions> getSolvedQuestions() {
        return solvedQuestions;
    }
    
    @Transient
    boolean solved;

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
    
    public void setSolvedQuestions(List<SolvedQuestions> solvedQuestions) {
        this.solvedQuestions = solvedQuestions;
    }

    public SolvedQuizes(Long userId, Long quizId, Date solveDate) {
        this.userId = userId;
        this.quizId = quizId;
        this.solveDate = solveDate;
    }

    public SolvedQuizes() {
    }

    public Long getSolvedQuizId() {
        return solvedQuizId;
    }

    public void setSolvedQuizId(Long solvedQuizId) {
        this.solvedQuizId = solvedQuizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(Date solveDate) {
        this.solveDate = solveDate;
    }
    
}
