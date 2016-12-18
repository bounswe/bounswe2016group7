/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.rest;

import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.services.TagsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ugurbor
 */
@RestController
public class TagController {

    @Autowired
    TagsService tagsService;

    @RequestMapping(path = "/createTag", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Tags createTag(@RequestBody Tags tag) {
        return tagsService.createTag(tag);
    }
    
    @RequestMapping(path = "/addTag", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Tags addTag(@RequestBody Tags tag) {
        return new Tags();
    }
    
    @RequestMapping(path = "/getCategoryTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tags> getCategoryTags() {
        return tagsService.getCategoryTags();
    }
    
}
