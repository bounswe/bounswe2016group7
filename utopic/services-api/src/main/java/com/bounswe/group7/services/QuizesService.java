/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.repository.QuizesRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author myunu
 */
@Service
public class QuizesService {
    
    @Autowired
    QuizesRepository quizesRepository;
    
    @Autowired
    UsersService usersService;
    
    public Quizes createQuiz(Quizes quiz)
    {
        quiz.setUserId(usersService.getLoggedInUserId());
        quiz.setCreateDate(new Date());
        return quizesRepository.save(quiz);
    }
    
}
