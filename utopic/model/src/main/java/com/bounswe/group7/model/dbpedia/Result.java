package com.bounswe.group7.model.dbpedia;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Result implements Serializable {

    @SerializedName("uri")
    @Expose
    private String uri;

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("refCount")
    @Expose
    private Integer refCount;

    @SerializedName("classes")
    @Expose
    private List<Klaz> classes = new ArrayList<Klaz>();

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

    /**
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return The refCount
     */
    public Integer getRefCount() {
        return refCount;
    }

    /**
     *
     * @param refCount The refCount
     */
    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }

    public List<Klaz> getClasses() {
        return classes;
    }

    public void setClasses(List<Klaz> classes) {
        this.classes = classes;
    }

}
