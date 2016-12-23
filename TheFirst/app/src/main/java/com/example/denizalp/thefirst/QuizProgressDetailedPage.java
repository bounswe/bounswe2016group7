package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.QuizProgress;
import com.bounswe.group7.model.Topics;

import java.util.List;

public class QuizProgressDetailedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_progress_detailed_page);
        Intent previous = getIntent();
        int position = previous.getIntExtra("position",0);
        String token = previous.getStringExtra("token");
        TopicServiceClient topicServiceClient = new TopicServiceClient(token);
        UserServiceClient userServiceClient = new UserServiceClient(token);
        try {
            Long userId = userServiceClient.getLoggedInUser().getId();
            QuizProgress quizProgress = userServiceClient.getQuizProgress(userId).get(position);
            List<Topics> topicsList = quizProgress.getTopicPack().getTopics();
            ListView listView = (ListView) findViewById(R.id.progressDetailedListView);
            QuizProgressDetailedAdapter adapter = new QuizProgressDetailedAdapter(this, topicsList, token);
            listView.setAdapter(adapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
