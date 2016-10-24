/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.api.client;

import com.google.common.net.HttpHeaders;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author ugurbor
 */
public class BaseClient {

    private String API_URL = "http://localhost:8090/";

    protected Client client;
    private ObjectMapper mapper = null;

    public BaseClient() {
        client = new Client();
        mapper = new ObjectMapper();
    }

    public WebResource getResource() {
        WebResource resource = client.resource(API_URL);
        return resource;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public <T> T get(WebResource resource, TypeReference<T> typeReference) throws Exception {
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String jsonStr = response.getEntity(String.class);
        T responseEntity = getMapper().readValue(jsonStr, typeReference);
        return responseEntity;
    }

    public <T> T post(WebResource resource, TypeReference<T> typeReference, Object requestEntity) throws Exception {
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, getMapper().writeValueAsBytes(requestEntity));
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String jsonStr = response.getEntity(String.class);
        T responseEntity = getMapper().readValue(jsonStr, typeReference);
        return responseEntity;
    }
}
