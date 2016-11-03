/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.Questions;
import com.bounswe.group7.services.QuestionsService;
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
public class QuestionsController {
    
    @Autowired
    QuestionsService questionsService;
    
    @RequestMapping(path = "/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Questions addQuestion(@RequestBody Questions question)
    {
        return questionsService.addQuestion(question);
    }
}
