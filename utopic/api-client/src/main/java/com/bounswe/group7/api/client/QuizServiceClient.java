/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuizes;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author myunu
 */
public class QuizServiceClient extends BaseClient{
    public QuizServiceClient() {
    }

    public QuizServiceClient(String token) {
        super(token);
    }
    
    public Quizes createQuiz(Quizes quiz) throws Exception {
        return post(getResource().path("createQuiz"), new TypeToken<Quizes>() {
        }, quiz);
    }
    
    public Quizes getQuiz(Long topicId) throws Exception {
        return post(getResource().path("getQuiz"), new TypeToken<Quizes>() {
        }, topicId);
    }
    
    public SolvedQuizes solveQuiz(Quizes quiz) throws Exception {
        return post(getResource().path("solveQuiz"), new TypeToken<SolvedQuizes>() {
        }, quiz);
    }
    
    public Questions addQuestion(Questions question) throws Exception {
        return post(getResource().path("addQuestion"), new TypeToken<Questions>() {
        }, question);
    }
    
    public SolvedQuizes isQuizSolved(Long quizId) throws Exception{
        return post(getResource().path("isQuizSolved"), new TypeToken<SolvedQuizes>() {
        }, quizId);
    }
}
