package com.bounswe.group7.repository;

import com.bounswe.group7.model.Topics;
import org.springframework.data.repository.CrudRepository;
import com.bounswe.group7.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by stephan on 20.03.16.
 */
public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByEmail(String email);

    @Query(value = "SELECT * FROM USERS WHERE USERNAME = ?1 OR EMAIL = ?1", nativeQuery = true)
    Users findByEmailOrUsername(String username);
    
    @Query(value = "SELECT T.* from USERS T, FOLLOWED_TOPICS FT WHERE FT.TOPIC_ID = :topicId and FT.USER_ID = T.ID", nativeQuery = true)
    public List<Users> findUsersByTopicId(@Param("topicId") Long topicId);

}
