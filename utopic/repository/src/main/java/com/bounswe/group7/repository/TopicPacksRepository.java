/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.repository;

import com.bounswe.group7.model.TopicPacks;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Batuhan
 */
public interface TopicPacksRepository extends CrudRepository<TopicPacks, Long> {

    public List<TopicPacks> findByUserIdOrderByCreateDateDesc(Long userId);

    public TopicPacks findByTopicPackId(Long topicPackId);
    
    public TopicPacks findByUserIdAndName(Long userId, String name);
    
    @Query(value = "select tp.* from solved_quizes sq, quizes q, topics t, topic_packs tp where sq.user_id = :userId and q.quiz_id = sq.quiz_id and q.topic_id = t.topic_id and t.topic_pack_id = tp.topic_pack_id group by tp.topic_pack_id", nativeQuery = true)
    public List<TopicPacks> findTopicPacksWithSolvedQuizes(@Param("userId") Long userId);
}
