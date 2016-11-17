package com.bounswe.group7.model.dbpedia;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Category {

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("label")
    @Expose
    private String label;

    /**
     *
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return The label
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
