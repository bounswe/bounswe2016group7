/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuizes;
import com.bounswe.group7.services.QuizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author myunu
 */
@RestController
public class QuizesController {

    @Autowired
    QuizesService quizesService;

    @RequestMapping(path = "/createQuiz", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Quizes createQuiz(@RequestBody Quizes quiz) {
        return quizesService.createQuiz(quiz);
    }

    @RequestMapping(path = "getQuiz", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public Quizes getQuiz(@RequestBody Long topicId) {
        return quizesService.getQuiz(topicId);
    }
    
    @RequestMapping(path = "solveQuiz", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public SolvedQuizes solveQuiz(@RequestBody Quizes quiz) {
        return quizesService.solveQuiz(quiz);
    }
    
    @RequestMapping(path = "isQuizSolved", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN', 'EXPLORER')")
    public SolvedQuizes isQuizSolved(@RequestBody Long quizId) {
        return quizesService.isQuizSolved(quizId);
    }

}
