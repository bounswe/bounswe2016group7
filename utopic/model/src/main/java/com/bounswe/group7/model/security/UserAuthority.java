/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model.security;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ugurbor
 */
@Entity
@Table(name = "USER_AUTHORITY")
public class UserAuthority {

    @Id
    @Column(name = "RECORD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recordId;

    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "AUTHORITY_ID")
    private int authorityId;

    public UserAuthority(Long userId, int authorityId) {
        this.userId = userId;
        this.authorityId = authorityId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

}
