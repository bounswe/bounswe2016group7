package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;

public class ShowTopicPage extends AppCompatActivity {

    TopicServiceClient topicServiceClient = null;
    UserServiceClient userServiceClient = null;
   // UserServiceClient userServiceClient = new UserServiceClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_topic_page);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        topicServiceClient = new TopicServiceClient(token);
        userServiceClient = new UserServiceClient(token);
        Intent intent = getIntent();
        Long topicId = intent.getLongExtra("topicId",0);
        Topics topic = null;
        try{
           topic = topicServiceClient.getTopic(topicId);
            setTopicValues(topic);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setTopicValues(Topics t){
        if(t != null) {
            TextView topicHeader = (TextView) findViewById(R.id.topicHeader);
            TextView topicCreator = (TextView) findViewById(R.id.topicCreator);
            TextView topicDate = (TextView) findViewById(R.id.topicDate);
            EditText topicDescription = (EditText) findViewById(R.id.topicDescription);
            topicDescription.setKeyListener(null);
            TextView topicRate = (TextView) findViewById(R.id.topicRate);
            EditText topicContent = (EditText) findViewById(R.id.topicContent);
            topicContent.setKeyListener(null);

            try {
                Users users = userServiceClient.getUser(t.getUserId());

                System.out.println(t.getHeader());
                topicHeader.setText(t.getHeader());

                System.out.println(users.getUsername());
                topicCreator.setText(users.getUsername());

                System.out.println(t.getCreateDate().toString());
                topicDate.setText(t.getCreateDate().toString());

                System.out.println(t.getDescription());
                topicDescription.setText(t.getDescription());

                String rate = "" + t.getRate();
                System.out.println(rate);
                topicRate.setText(rate);

                System.out.println(t.getContent());
                topicContent.setText(t.getContent());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void goHOme(View v) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void logOut(View v){
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.clear();
        prefEditor.apply();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goUSerPage(View v){
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }

}
