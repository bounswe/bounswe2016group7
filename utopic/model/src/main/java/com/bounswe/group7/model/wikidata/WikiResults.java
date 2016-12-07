
package com.bounswe.group7.model.wikidata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WikiResults {

    @SerializedName("searchinfo")
    @Expose
    private Searchinfo searchinfo;
    @SerializedName("search")
    @Expose
    private List<Search> search = null;
    @SerializedName("search-continue")
    @Expose
    private Integer searchContinue;
    @SerializedName("success")
    @Expose
    private Integer success;

    /**
     * 
     * @return
     *     The searchinfo
     */
    public Searchinfo getSearchinfo() {
        return searchinfo;
    }

    /**
     * 
     * @param searchinfo
     *     The searchinfo
     */
    public void setSearchinfo(Searchinfo searchinfo) {
        this.searchinfo = searchinfo;
    }

    /**
     * 
     * @return
     *     The search
     */
    public List<Search> getSearch() {
        return search;
    }

    /**
     * 
     * @param search
     *     The search
     */
    public void setSearch(List<Search> search) {
        this.search = search;
    }

    /**
     * 
     * @return
     *     The searchContinue
     */
    public Integer getSearchContinue() {
        return searchContinue;
    }

    /**
     * 
     * @param searchContinue
     *     The search-continue
     */
    public void setSearchContinue(Integer searchContinue) {
        this.searchContinue = searchContinue;
    }

    /**
     * 
     * @return
     *     The success
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

}
