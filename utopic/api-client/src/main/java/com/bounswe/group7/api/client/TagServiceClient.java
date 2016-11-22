/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.Topics;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author ugurbor
 */
public class TagServiceClient extends BaseClient{

    public TagServiceClient() {
    }

    public TagServiceClient(String token) {
        super(token);
    }
    
    public Tags createTag(Tags tag) throws Exception{
        return post(getResource().path("createTag"), new TypeToken<Tags>() {
        }, tag);
    }
    
    public Tags addTag(Tags tag) throws Exception {
        return post(getResource().path("addTag"), new TypeToken<Tags>() {
        }, tag);
    }

}
