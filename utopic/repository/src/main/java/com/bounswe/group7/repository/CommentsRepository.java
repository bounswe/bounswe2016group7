/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.repository;

import com.bounswe.group7.model.Comments;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author myunu
 */
public interface CommentsRepository extends CrudRepository<Comments, Long>{
    
    public List<Comments> findByUserIdOrderByCreateDateDesc(Long userId);
    
    public List<Comments> findByTopicIdOrderByCreateDateDesc(Long topicId);
}
