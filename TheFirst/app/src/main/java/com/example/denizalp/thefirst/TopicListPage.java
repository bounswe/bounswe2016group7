package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Topics;
import android.support.v7.app.ActionBar.*;
import org.glassfish.hk2.api.messaging.Topic;

import java.util.List;

public class TopicListPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        TopicServiceClient topicServiceClient = new TopicServiceClient(token);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Intent intent = getIntent();
        Intent toTopic = new Intent(this,ShowTopicPage.class);
        int recentOrTop = intent.getIntExtra("recentOrTop",1);
        List<Topics> topicList = null;
        if(recentOrTop == 1) {
            try {
                topicList = topicServiceClient.getRecentTopics();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else {
            try {
                topicList = topicServiceClient.getTopTopics();
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
                linearLayout.addView(button);
            }
        }
        setContentView(linearLayout);
        //setContentView(R.layout.activity_topic_list_page);
    }
}
