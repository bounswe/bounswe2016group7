/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

/**
 *
 * @author myunu
 */
public class UserLink {
    public long id;
    public String username;

    public UserLink() {
    }

    public UserLink(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
