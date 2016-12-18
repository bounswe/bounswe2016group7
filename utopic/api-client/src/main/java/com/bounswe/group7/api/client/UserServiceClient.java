/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.QuizProgress;
import com.bounswe.group7.model.Users;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author ugurbor
 */
public class UserServiceClient extends BaseClient {

    public UserServiceClient(String token) {
        super(token);
    }

    public String changePassword(Users request) throws Exception {
        return post(getResource().path("changePassword"), new TypeToken<String>() {
        }, request);
    }

    public Users getLoggedInUser() throws Exception {
        return get(getResource().path("getLoggedInUser"), new TypeToken<Users>() {
        });
    }

    public Users getUser(Long userId) throws Exception {
        return post(getResource().path("public/getUser"), new TypeToken<Users>() {
        }, userId);
    }

    public List<QuizProgress> getQuizProgress() throws Exception {
        return get(getResource().path("getQuizProgress"), new TypeToken<List<QuizProgress>>() {
        });
    }
}
