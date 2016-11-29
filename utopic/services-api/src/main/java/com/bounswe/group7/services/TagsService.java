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
        Tags tag = tagRepository.findByLabelAndCategory(toBeCreated.getLabel(), toBeCreated.getCategory());
        
        if(tag == null)
        {
            toBeCreated.setRefCount(1);
            return tagRepository.save(toBeCreated);
        }
        else
        {
            tag.setRefCount(tag.getRefCount()+1);
            return tagRepository.save(tag);
        }
    }
   
}
