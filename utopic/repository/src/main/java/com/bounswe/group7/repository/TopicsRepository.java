/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.repository;

import com.bounswe.group7.model.Topics;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author myunu
 */
public interface TopicsRepository extends CrudRepository<Topics, Long> {

    public List<Topics> findByUserId(Long userId);
    
    public List<Topics> findByUserIdOrderByCreateDateDesc(Long userId);

    public List<Topics> findTop10ByOrderByCreateDateDesc();
    
    public List<Topics> findTop10ByOrderByRateDesc();

    public List<Topics> findByIgnoreCaseHeaderContaining(String headerLike);
    
    public List<Topics> findByIgnoreCaseDescriptionContaining(String headerLike);
    
    public List<Topics> findByIgnoreCaseContentContaining(String headerLike);
}
