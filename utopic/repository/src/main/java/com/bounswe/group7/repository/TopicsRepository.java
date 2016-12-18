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
import org.springframework.data.repository.query.Param;

/**
 *
 * @author myunu
 */
public interface TopicsRepository extends CrudRepository<Topics, Long> {

    public List<Topics> findByUserId(Long userId);

    public Topics findByTopicPackIdAndOrderBy(Long topicPackId, int orderBy);
    
    public List<Topics> findByUserIdOrderByCreateDateDesc(Long userId);

    public List<Topics> findTop10ByOrderByCreateDateDesc();

    public List<Topics> findTop10ByOrderByRateDesc();

    public List<Topics> findByIgnoreCaseHeaderContaining(String headerLike);

    public List<Topics> findByIgnoreCaseDescriptionContaining(String headerLike);

    public List<Topics> findByIgnoreCaseContentContaining(String headerLike);

    @Query(value = "SELECT T.* FROM topics T, TOPIC_TAGS TT, TAGS TG WHERE T.TOPIC_ID = TT.TOPIC_ID and TT.TAG_ID = TG.TAG_ID and TG.category like %:category%", nativeQuery = true)
    public List<Topics> findByTags(@Param("category") String category);

    @Query(value = "SELECT T.* from TOPICS T, FOLLOWED_TOPICS FT WHERE FT.USER_ID = :userId and FT.TOPIC_ID = T.TOPIC_ID", nativeQuery = true)
    public List<Topics> findAllByUserId(@Param("userId") Long userId);
    
    @Query(value = "SELECT T.* from TOPICS T, TOPIC_PACKS FT WHERE FT.TOPIC_PACK_ID = :topicPackId AND T.TOPIC_PACK_ID = FT.TOPIC_PACK_ID ORDER BY order_by asc", nativeQuery = true)
    public List<Topics> findTopicsOfTopicPack(@Param("topicPackId") Long topicPackId);
    
    @Query(value = "SELECT T.* FROM topics T, topic_packs TP WHERE T.TOPIC_PACK_ID = TP.TOPIC_PACK_ID and lower(TP.name) like %:topicPackName%", nativeQuery = true)
    public List<Topics> findByTopicPack(@Param("topicPackName") String topicPackName);
 
        
    @Query(value = "select t.* from solved_quizes sq, quizes q, topics t, topic_packs tp where sq.user_id = :userId and t.topic_pack_id = :topicPackId and q.quiz_id = sq.quiz_id and q.topic_id = t.topic_id and t.topic_pack_id = tp.topic_pack_id group by t.topic_id", nativeQuery = true)
    public List<Topics> findTopicsWithSolvedQuizes(@Param("userId") Long userId, @Param("topicPackId") Long topicPackId);
}
