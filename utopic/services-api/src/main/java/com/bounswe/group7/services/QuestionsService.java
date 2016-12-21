/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Questions;
import com.bounswe.group7.repository.QuestionsRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service class of the questions of the quiz.
 * @author Yunus Seker
 */
@Service
public class QuestionsService {
    
    @Autowired
    QuestionsRepository questionsRepository;
    
    @Autowired
    UsersService usersService;
    
    /**
     * This method takes a question
     * and adds it to the database.
     * @param question Questions object that holds question and options
     * @return returns added question as object
     */
    public Questions addQuestion(Questions question)
    {
        question.setDateCreated(new Date());
        return questionsRepository.save(question);
    }
    
}
