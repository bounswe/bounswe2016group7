/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuizes;
import com.bounswe.group7.repository.QuizesRepository;
import com.bounswe.group7.repository.SolvedQuizesRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
    SolvedQuizesRepository solvedQuizesRepository;

    @Autowired
    UsersService usersService;

    public Quizes createQuiz(Quizes quiz) {
        quiz.setUserId(usersService.getLoggedInUserId());
        quiz.setCreateDate(new Date());
        return quizesRepository.save(quiz);
    }

    public Quizes getQuiz(Long quizId) {
        return quizesRepository.findOne(quizId);
    }

    public SolvedQuizes solveQuiz(Quizes quiz) {
        List<Questions> trueAnswers = getQuiz(quiz.getQuizId()).getQuestions();
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
        for (int i = 0; i < size; i++) {
            if (trueAnswers.get(i).getRightAnswer() == userAnswers.get(i).getChosenAnswer()) {
                count++;
            }
        }
        Double score = (count * 100.0) / size;
        SolvedQuizes solvedQuiz = new SolvedQuizes(usersService.getLoggedInUserId(), quiz.getQuizId(), score, new Date());
        return solvedQuizesRepository.save(solvedQuiz);
    }
}
