/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.dbpedia;

import com.bounswe.group7.model.wikidata.WikiResults;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author ugurbor
 */
public class WikidataIntegration {

    final static String API_URL = "https://www.wikidata.org/w/api.php";//?action=wbsearchentities&search=orange&language=en&format=json&limit=10";

    public static void main(String[] args) {
        WikiResults wikiRes = search("orange");
        System.out.println(wikiRes.getSearch());
    }

    public static WikiResults search(String keyword) {
        StringBuilder str = new StringBuilder();
        WikiResults wikiRes = new WikiResults();
        try {
            URL url = new URL(API_URL + "?action=wbsearchentities&language=en&format=json&search=" + keyword);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                str.append(inputLine);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<WikiResults>() {
            }.getType();
            wikiRes = gson.fromJson(str.toString(), type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return wikiRes;
    }
}
