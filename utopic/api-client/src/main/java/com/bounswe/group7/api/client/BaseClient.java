/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 *
 * @author ugurbor
 */
public class BaseClient {

    //private String API_URL = "http://54.93.106.33:8090/";
    private String API_URL = "http://localhost:8090/"; // To Practice backend on Local, activate this line. 

    protected Client client;
    private Gson gson;
    private String token;

    public BaseClient() {
        this(null);
    }

    public BaseClient(String token) {
        this.token = token;
        client = ClientBuilder.newClient().register(AndroidFriendlyFeature.class).register(GsonMessageBodyHandler.class);
        gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.getTime());
            }
        }).registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
                try {
                    long timeStamp = json.getAsLong();
                    java.sql.Date sqlDate = new java.sql.Date(timeStamp);
                    java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                    return utilDate;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        }).create();
    }

    public WebTarget getResource() {
        WebTarget resource = client.target(API_URL);
        return resource;
    }

    public <T> T get(WebTarget resource, TypeToken<T> type) throws Exception {
        Response response = resource.request(MediaType.APPLICATION_JSON).header("Authorization", token).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String jsonStr = response.readEntity(String.class);
        T responseEntity = gson.fromJson(jsonStr, type.getType());
        return responseEntity;
    }

    public <T> T post(WebTarget resource, TypeToken<T> type, Object requestEntity) throws Exception {
        Response response = resource.request(MediaType.APPLICATION_JSON).header("Authorization", token).post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON));
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String jsonStr = response.readEntity(String.class);
        T responseEntity = gson.fromJson(jsonStr, type.getType());
        return responseEntity;
    }

    public static class AndroidFriendlyFeature implements Feature {

        @Override
        public boolean configure(FeatureContext context) {
            context.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    addUnbindFilter(new Filter() {
                        @Override
                        public boolean matches(Descriptor d) {
                            String implClass = d.getImplementation();
                            return implClass.startsWith(
                                    "org.glassfish.jersey.message.internal.DataSource")
                                    || implClass.startsWith(
                                            "org.glassfish.jersey.message.internal.RenderedImage");
                        }
                    });
                }
            });
            return true;
        }
    }

}
