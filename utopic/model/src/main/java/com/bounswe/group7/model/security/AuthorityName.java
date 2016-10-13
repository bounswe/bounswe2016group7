package com.bounswe.group7.model.security;

public enum AuthorityName {
    ROLE_USER(1, "ROLE_USER"), ROLE_ADMIN(2,"ROLE_ADMIN");
    private int id;
    private String name;

    private AuthorityName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}