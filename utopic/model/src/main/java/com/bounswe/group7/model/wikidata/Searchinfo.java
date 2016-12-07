
package com.bounswe.group7.model.wikidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Searchinfo {

    @SerializedName("search")
    @Expose
    private String search;

    /**
     * 
     * @return
     *     The search
     */
    public String getSearch() {
        return search;
    }

    /**
     * 
     * @param search
     *     The search
     */
    public void setSearch(String search) {
        this.search = search;
    }

}
