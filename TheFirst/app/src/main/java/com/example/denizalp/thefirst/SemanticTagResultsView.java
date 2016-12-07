package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SemanticTagResultsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semantic_tag_results_view);
        Intent previous = getIntent();
        String url = previous.getStringExtra("url");

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            String output = response.toString();
            //System.out.println(output.substring(6,output.length()-1));
            output = output.substring(6,output.length()-1);
            JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
            //System.out.println(jsonObject.toString());
            JsonArray jsonArray = jsonObject.getAsJsonArray("search");
            //System.out.println(jsonArray.toString());
            int arrSize = jsonArray.size();
            for(int i=0; i<arrSize; i++){
                JsonElement element = jsonArray.get(i);
                System.out.print(element.getAsJsonObject().getAsJsonPrimitive("label").getAsString()+": ");
                System.out.print(element.getAsJsonObject().getAsJsonPrimitive("description").getAsString()+"\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
