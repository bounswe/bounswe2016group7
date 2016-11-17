package com.bounswe.group7.model.dbpedia;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

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
    private List<Class> classes = new ArrayList<Class>();

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

    /**
     *
     * @return The classes
     */
    public List<Class> getClasses() {
        return classes;
    }

    /**
     *
     * @param classes The classes
     */
    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

}
