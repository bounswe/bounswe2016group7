package com.example.denizalp.thefirst;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.QuizProgress;

import java.util.List;

public class QuizProgressPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_progress_page);
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String currentToken = sharedPref.getString("currentToken","");
        UserServiceClient userServiceClient = new UserServiceClient(currentToken);
        try{
            Long userId = userServiceClient.getLoggedInUser().getId();
            List<QuizProgress> quizProgressList = userServiceClient.getQuizProgress(userId);
            ListView listView = (ListView) findViewById(R.id.progressListView);
            QuizProgressAdapter adapter = new QuizProgressAdapter(this, quizProgressList, currentToken);
            listView.setAdapter(adapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
