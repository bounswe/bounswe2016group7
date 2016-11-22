/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.model.dbpedia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

/**
 *
 * @author ugurbor
 */
@Generated("org.jsonschema2pojo")
public class Klaz {

    @SerializedName("uri")
    @Expose
    private String uri;

    @SerializedName("label")
    @Expose
    private String label;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
