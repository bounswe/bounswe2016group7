package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopicPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_page);
    }

    public void goEditing(View v){
        EditText header = (EditText) findViewById(R.id.editText2);
        EditText description = (EditText) findViewById(R.id.editText);
        Date createDate = new Date();

        String topicHeader = header.getText().toString();
        String topicDescription = description.getText().toString();

        Bundle topicBundle = new Bundle();
        topicBundle.putString("topicHeader",topicHeader);
        topicBundle.putString("topicDescription",topicDescription);
        topicBundle.putSerializable("topicDate",createDate);

        Intent intent = new Intent(this, TopicAddMaterialQuiz.class);
        intent.putExtras(topicBundle);
        startActivity(intent);
    }
}
