package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
* EmptyQuizPage class
* If a topic does not have a Quiz,this empty quiz page is shown
* */
public class EmptyQuizPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_quiz_page);  //set view as activity_empty_quiz_page.xml
        Intent previous = getIntent();
        Long userId = previous.getLongExtra("userId",0);
        Long creatorId = previous.getLongExtra("creatorId",-1);
        //if the page is seen by a user who is not the creator of the topic page
        //then he/she can not have the ability to create Quiz on that page
        //should not be able to see create quiz button, set tne button as invisible
        if(userId != creatorId) {
            Button b = (Button) findViewById(R.id.goCreateQuiz);
            b.setVisibility(View.GONE);
        }
    }

    //goQuizCreation Method
    // if this empty page is viewed by the author of the topic then there should be quiz create option
    //this method enables that feature
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
