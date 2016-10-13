/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.security.repository;

import org.springframework.data.repository.CrudRepository;
import com.bounswe.group7.model.security.Authority;

/**
 *
 * @author ugurbor
 */
public interface AuthorityRepository extends CrudRepository<Authority, Long>{
    
}
