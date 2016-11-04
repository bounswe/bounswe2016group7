/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.repository;

import com.bounswe.group7.model.Quizes;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author myunu
 */
public interface QuizesRepository extends CrudRepository<Quizes, Long>{
    
    public Quizes findByUserId(Long userId);
    
}