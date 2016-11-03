/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.repository;

import com.bounswe.group7.model.Questions;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author myunu
 */
public interface QuestionsRepository extends CrudRepository<Questions, Long> {
    public List<Questions> findByQuizId(Long quizId);
    public List<Questions> findByUserId(Long userId);
}
