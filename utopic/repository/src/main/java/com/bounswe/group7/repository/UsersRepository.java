package com.bounswe.group7.repository;

import org.springframework.data.repository.CrudRepository;
import com.bounswe.group7.model.Users;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by stephan on 20.03.16.
 */
public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByEmail(String email);

    @Query(value = "SELECT * FROM USERS WHERE USERNAME = ?1 OR EMAIL = ?1", nativeQuery = true)
    Users findByEmailOrUsername(String username);
}
