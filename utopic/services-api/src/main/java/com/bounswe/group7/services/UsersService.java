/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.QuizProgress;
import com.bounswe.group7.model.SolvedQuizes;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.repository.QuizesRepository;
import com.bounswe.group7.repository.SolvedQuizesRepository;
import com.bounswe.group7.repository.TopicPacksRepository;
import com.bounswe.group7.repository.TopicsRepository;
import com.bounswe.group7.repository.UsersRepository;
import com.bounswe.group7.security.JwtTokenUtil;
import com.bounswe.group7.security.JwtUser;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ugurbor
 */
@Service
public class UsersService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TopicPacksRepository topicPacksRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private SolvedQuizesRepository solvedQuizesRepository;

    @Autowired
    private QuizesRepository quizesRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Users updateUser(Users user) {
        return usersRepository.save(user);
    }

    public Users getLoggedInUser() {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Users user = usersRepository.findByUsername(username);
        return user;
    }

    public Long getLoggedInUserId() {
        return getLoggedInUser().getId();
    }

    public Users getUser(Long userId) {
        return usersRepository.findOne(userId);
    }

    public String changePassword(Users changePasswordReq) {
        Users user = usersRepository.findOne(getLoggedInUserId());
        if (passwordEncoder().matches(changePasswordReq.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder().encode(changePasswordReq.getNewPassReq()));
            usersRepository.save(user);
            return "password changed";
        }

        return "not changed";
    }

    public List<QuizProgress> getQuizProgress() {
        Long userId = getLoggedInUserId();
        List<QuizProgress> quizProgresses = new ArrayList<QuizProgress>();
        List<TopicPacks> topicPacks = topicPacksRepository.findTopicPacksWithSolvedQuizes(userId);
        for (TopicPacks thePack : topicPacks) {
            QuizProgress prog = new QuizProgress();
            prog.setTotalProgress(0.0);
            prog.setTopicPack(thePack);
            List<Topics> solvedTopics = topicsRepository.findTopicsWithSolvedQuizes(userId, thePack.getTopicPackId());
            for (Topics t : solvedTopics) {
                SolvedQuizes sq = solvedQuizesRepository.findByUserIdAndQuizId(userId, quizesRepository.findByTopicId(t.getTopicId()).getQuizId());
                t.setScore(sq.getScore());
                prog.setTotalProgress(prog.getTotalProgress() + sq.getScore() / thePack.getCount());
            }
            prog.getTopicPack().setTopics(solvedTopics);
            quizProgresses.add(prog);
        }

        return quizProgresses;
    }
}
