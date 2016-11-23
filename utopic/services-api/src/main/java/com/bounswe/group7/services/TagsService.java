/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.model.Tags;
import com.bounswe.group7.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ugurbor
 */
@Service
public class TagsService {
    @Autowired
    TagsRepository tagRepository;
    
    public Tags createTag(Tags toBeCreated){
        toBeCreated.setRefCount(0);
        toBeCreated.setCategory("manual");
        if(tagRepository.findByLabelAndCategory(toBeCreated.getLabel(), toBeCreated.getCategory()) == null)
            return tagRepository.save(toBeCreated);
        else
            return tagRepository.findByLabelAndCategory(toBeCreated.getLabel(), toBeCreated.getCategory());
    }
   
}
