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
 *
 * @author myunu
 */
@Service
public class QuestionsService {
    
    @Autowired
    QuestionsRepository questionsRepository;
    
    @Autowired
    UsersService usersService;
    
    public Questions addQuestion(Questions question)
    {
        question.setUserId(usersService.getLoggedInUserId());
        question.setDateCreated(new Date());
        return questionsRepository.save(question);
    }
    
}
