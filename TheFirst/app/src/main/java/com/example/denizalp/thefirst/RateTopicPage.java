package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.RatedTopics;

import java.util.ArrayList;

public class RateTopicPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_topic_page);
        Spinner rateSpinner = (Spinner) findViewById(R.id.ratingSpinner);
        ArrayList<Integer> rateList = new ArrayList<Integer>();
        rateList.add(1); rateList.add(2); rateList.add(3); rateList.add(4); rateList.add(5);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, rateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateSpinner.setAdapter(adapter);
    }

    public void ratingTopic(View v) throws Exception{
        Spinner rateSpinner = (Spinner) findViewById(R.id.ratingSpinner);
        int rating = (int) rateSpinner.getSelectedItem();
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",0);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        TopicServiceClient topicServiceClient = new TopicServiceClient(token);
        UserServiceClient userServiceClient = new UserServiceClient(token);
        RatedTopics ratedTopics = new RatedTopics();
        ratedTopics.setTopicId(topicId);
        ratedTopics.setUserId(userServiceClient.getLoggedInUser().getId());
        ratedTopics.setRate(rating);
        Boolean isRated = topicServiceClient.rateTopic(ratedTopics);
        if(isRated) {
           System.out.println("You have successfully rated the topic!");
        }
        Intent intent = new Intent(this, ShowTopicPage.class);
        intent.putExtra("topicId",topicId);
        startActivity(intent);
    }
}
