/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.dbpedia;

import com.bounswe.group7.model.dbpedia.Results;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ugurbor
 */
public class DBpediaIntegration {

    final String API_URL = "http://lookup.dbpedia.org/api/search/";

    public static void main(String[] args) {
        DBpediaIntegration integ = new DBpediaIntegration();
        Results res = integ.keywordSearch("mocha");
        System.out.println();
    }

    public Results prefixSearch(String prefix) {
        Results results = new Results();
        StringBuilder str = new StringBuilder();
        try {
            URL url = new URL(API_URL + "PrefixSearch?QueryClass=&MaxHits=5&QueryString=" + prefix);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                str.append(inputLine);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Results>() {
            }.getType();
            results = gson.fromJson(str.toString(), type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    public Results keywordSearch(String keyword) {
        Results results = new Results();
        StringBuilder str = new StringBuilder();
        try {
            URL url = new URL(API_URL + "KeywordSearch?QueryClass=&QueryString=" + keyword);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                str.append(inputLine);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Results>() {
            }.getType();
            results = gson.fromJson(str.toString(), type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
