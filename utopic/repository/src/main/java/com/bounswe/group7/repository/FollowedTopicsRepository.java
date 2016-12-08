/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.repository;

import com.bounswe.group7.model.FollowedTopics;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author myunu
 */
public interface FollowedTopicsRepository extends CrudRepository<FollowedTopics, Long>{
    public FollowedTopics findByUserIdAndTopicId(Long userId, Long topicId);
    
    public List<FollowedTopics> findAllByTopicId(Long topicId);
}
