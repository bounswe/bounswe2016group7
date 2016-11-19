/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author ugurbor
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"label", "category"}))
public class Tags implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_SEQ")
    @SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "tag_id", nullable = false)
    private Long tadId;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "ref_count", nullable = false)
    private Integer refCount;

    @Column(name = "category", nullable = false)
    private String category;

    public Long getTadId() {
        return tadId;
    }

    public void setTadId(Long tadId) {
        this.tadId = tadId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getRefCount() {
        return refCount;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
