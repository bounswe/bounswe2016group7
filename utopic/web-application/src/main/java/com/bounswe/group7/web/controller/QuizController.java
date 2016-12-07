/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.QuizServiceClient;
import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuizes;
import com.bounswe.group7.web.domain.QuestionAndAnswer;
import com.bounswe.group7.web.domain.QuestionAnswers;
import com.bounswe.group7.web.domain.QuizResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Batuhan
 */
@RestController
public class QuizController {
    
    @RequestMapping(value = "/solvequiz", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String solveQuiz(HttpServletRequest request,@RequestBody QuestionAnswers questionAnswers){
        HttpSession session = request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        QuizServiceClient quizClient = new QuizServiceClient((String) session.getAttribute("token"));
        Quizes quizToBeSolved = new Quizes();
        try{
            quizToBeSolved.setQuizId(questionAnswers.quizId);
            List<Questions> questions = new ArrayList();
            for(QuestionAndAnswer qna : questionAnswers.questionList){
                Questions question = new Questions();
                question.setQuestionId(qna.questionId.longValue());
                question.setChosenAnswer((char)('A' + qna.optionId.intValue()));
                questions.add(question);
            }
            quizToBeSolved.setQuestions(questions);
            SolvedQuizes solvedQuiz = quizClient.solveQuiz(quizToBeSolved);
            double score = solvedQuiz.getScore();
            int totalNumber = questions.size();
            int correctNumber = (int)(totalNumber * (score / 100));
            int wrongNumber = totalNumber - correctNumber;
            //Third parameter will be changed, it is wrong now.
            QuizResult result = new QuizResult(correctNumber, wrongNumber, questionAnswers.questionList);
            String resultJson = mapper.writeValueAsString(result);
            return resultJson;
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
}
