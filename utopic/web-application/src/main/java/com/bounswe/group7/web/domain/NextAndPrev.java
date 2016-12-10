/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.domain;

/**
 *
 * @author Batuhan
 */
public class NextAndPrev {
    public Long nextId;
    public Long prevId;
    public String nextName;
    public String prevName;

    public NextAndPrev() {
    }

    public NextAndPrev(Long nextId, Long prevId, String nextName, String prevName) {
        this.nextId = nextId;
        this.prevId = prevId;
        this.nextName = nextName;
        this.prevName = prevName;
    }
    
}
