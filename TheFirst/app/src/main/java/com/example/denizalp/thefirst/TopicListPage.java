package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bounswe.group7.api.client.SearchServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;

import android.support.v7.app.ActionBar.*;
import org.glassfish.hk2.api.messaging.Topic;

import java.util.List;
/*
* TopicListPage class
* Lists the topicPages
*
* */
public class TopicListPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        TopicServiceClient topicServiceClient = new TopicServiceClient(token);  // create topicServiceClient
        UserServiceClient userServiceClient = new UserServiceClient(token);   //create UserServiceClient
        LinearLayout linearLayout = new LinearLayout(this);   //set layout
        linearLayout.setBackgroundResource(R.drawable.wallpaper);
        linearLayout.setOrientation(LinearLayout.VERTICAL);   //set vertical layout
        Intent intent = getIntent();
        Intent toTopic = new Intent(this,ShowTopicPage.class);
        int recentOrTop = intent.getIntExtra("option",1);
        List<Topics> topicList = null;
        if(recentOrTop == 1) {
            try {
                topicList = topicServiceClient.getRecentTopics(); //list of Recent topics
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(recentOrTop == 2){
            try {
                topicList = topicServiceClient.getTopTopics();  //list of popular topics
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(recentOrTop == 3){
            try{
                Intent intent1 = getIntent();
                Long creatorID = intent1.getLongExtra("creatorID",0);
                Users theCreator = userServiceClient.getUser(creatorID);
                topicList = topicServiceClient.getUserTopics(theCreator.getId());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(recentOrTop == 4){
            try{
               topicList = topicServiceClient.getUserFollowedTopics();   //the topics that are followed by the user
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(recentOrTop == 5){
            try{
               Intent previous = getIntent();
                String keyword = previous.getStringExtra("keyword");  //get keyword
                String currToken = previous.getStringExtra("token");   //get token
                if(currToken.equalsIgnoreCase("")) System.out.println("Token boş gelmeyecek lan!");
                else {
                    SearchServiceClient searchServiceClient = new SearchServiceClient(currToken);
                    topicList = searchServiceClient.searchTopics(keyword);  //search the keyword among topics
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        if (topicList != null) {
            for(Topics topic : topicList) {
                Button button = new Button(this);
                button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                button.setText(topic.getHeader());
                int buttonId = topic.getTopicId().intValue();
                button.setId(buttonId);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toTopic.putExtra("topicId",topic.getTopicId());
                        startActivity(toTopic);
                    }
                });
                linearLayout.addView(button); //list the topics as buttons
            }
        }
        setContentView(linearLayout);
        //setContentView(R.layout.activity_topic_list_page);
    }
}
