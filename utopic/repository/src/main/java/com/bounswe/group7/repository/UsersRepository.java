package com.bounswe.group7.repository;

import org.springframework.data.repository.CrudRepository;
import com.bounswe.group7.model.Users;

/**
 * Created by stephan on 20.03.16.
 */
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
