/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.bounswe.group7.model.Users;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author ugurbor
 */
public class LoginServiceClient extends BaseClient {

    private final String PATH = "/";

    public LoginServiceClient() {

    }

    @Override
    public WebTarget getResource() {
        return super.getResource().path(PATH);
    }

    public Users login(Users request) throws Exception {
        return post(getResource().path("auth"), new TypeToken<Users>() {
        }, request);
    }

    public Users register(Users request) throws Exception {
        return post(getResource().path("register"), new TypeToken<Users>() {
        }, request);
    }

    public static void main(String[] args) {
        Users user = new Users();
        user.setUsername("ugurbor");
        user.setPassword("password");
        LoginServiceClient client = new LoginServiceClient();
        try {
            Users loggedIn = client.login(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
