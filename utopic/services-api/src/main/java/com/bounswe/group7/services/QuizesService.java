/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuestions;
import com.bounswe.group7.model.SolvedQuizes;
import com.bounswe.group7.repository.QuizesRepository;
import com.bounswe.group7.repository.SolvedQuestionsRepository;
import com.bounswe.group7.repository.SolvedQuizesRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service class of the quiz object.
 * @author Yunus Seker
 */
@Service
public class QuizesService {

    @Autowired
    QuizesRepository quizesRepository;

    @Autowired
    SolvedQuizesRepository solvedQuizesRepository;

    @Autowired
    SolvedQuestionsRepository solvedQuestionsRepository;

    @Autowired
    UsersService usersService;
    
    /**
     * This method takes a quiz object and 
     * assigns its date. After that it is added to database.
     * @param quiz quiz object that holds the userId,topicId,name and questions of the quiz.
     * @return returns added quiz object.
     */
    public Quizes createQuiz(Quizes quiz) {
        quiz.setUserId(usersService.getLoggedInUserId());
        quiz.setCreateDate(new Date());
        return quizesRepository.save(quiz);
    }
    
    /**
     * This method returns the quiz of a topic.
     * @param topicId the id of the topic
     * @return returns the quiz object
     */
    public Quizes getQuiz(Long topicId) {
        return quizesRepository.findByTopicId(topicId);
    }
    
    /**
     * This method takes a quiz and solves it like this:
     * It takes the answers from user, and takes the true
     * answers from database. questions are sorted in order
     * to their id's to be able to compare them.
     * @param quiz the quiz that holds the user answers.
     * @return returns a SolvedQuizes Object that has result and questions.
     */
    public SolvedQuizes solveQuiz(Quizes quiz) {
        SolvedQuizes solvedQuiz = new SolvedQuizes(usersService.getLoggedInUserId(), quiz.getQuizId(), new Date());
        solvedQuiz = solvedQuizesRepository.save(solvedQuiz);
        List<SolvedQuestions> solvedQuestions = new ArrayList<>();
        List<Questions> trueAnswers = quizesRepository.findOne(quiz.getQuizId()).getQuestions();
        List<Questions> userAnswers = quiz.getQuestions();
        Collections.sort(userAnswers, new Comparator<Questions>() {
            public int compare(Questions a, Questions b) {
                return (int) (a.getQuestionId() - b.getQuestionId());
            }
        });
        Collections.sort(trueAnswers, new Comparator<Questions>() {
            public int compare(Questions a, Questions b) {
                return (int) (a.getQuestionId() - b.getQuestionId());
            }
        });
        int size = trueAnswers.size(), count = 0;
        boolean trueOrFalse;
        for (int i = 0; i < size; i++) {
            trueOrFalse = false;
            if (trueAnswers.get(i).getRightAnswer() == userAnswers.get(i).getChosenAnswer()) {
                trueOrFalse = true;
                count++;
            }
            solvedQuestions.add(
                    solvedQuestionsRepository.save(new SolvedQuestions(
                            solvedQuiz.getSolvedQuizId(),
                            trueAnswers.get(i).getQuestionId(),
                            trueAnswers.get(i).getRightAnswer(),
                            userAnswers.get(i).getChosenAnswer(),
                            trueOrFalse)));
        }
        Double score = (count * 100.0) / size;
        solvedQuiz.setScore(score);
        solvedQuiz.setSolvedQuestions(solvedQuestions);
        return solvedQuizesRepository.save(solvedQuiz);
    }
    
    public SolvedQuizes isQuizSolved(Long quizId)
    {
        SolvedQuizes sq = solvedQuizesRepository.findByUserIdAndQuizId(usersService.getLoggedInUserId(), quizId);
        if(sq == null)
        {
            sq = new SolvedQuizes();
            sq.setSolved(false);
        }
        else sq.setSolved(true);
        return sq;
    }
}
