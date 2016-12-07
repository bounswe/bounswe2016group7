package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmptyQuizPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_quiz_page);
        Intent previous = getIntent();
        Long userId = previous.getLongExtra("userId",0);
        Long creatorId = previous.getLongExtra("creatorId",-1);
        if(userId != creatorId) {
            Button b = (Button) findViewById(R.id.goCreateQuiz);
            b.setVisibility(View.GONE);
        }
    }

    public void goQuizCreation(View view){
        Intent previous = getIntent();
        Intent intent = new Intent(this, QuizCreatePage.class);
        String token = previous.getStringExtra("token");
        Long topicId = previous.getLongExtra("topicId",0);
        intent.putExtra("token",token);
        intent.putExtra("topicId",topicId);
        startActivity(intent);
    }
}
