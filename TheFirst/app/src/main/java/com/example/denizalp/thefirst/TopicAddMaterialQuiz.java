package com.example.denizalp.thefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Topics;

import java.util.Date;

public class TopicAddMaterialQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_add_material_quiz);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
    }

    public void createTopic(View view){
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        TopicServiceClient topicServiceClient = new TopicServiceClient(token);
        UserServiceClient userServiceClient = new UserServiceClient(token);

        try
        {
            Intent intent = getIntent();
            EditText content = (EditText) findViewById(R.id.editText5);

            String topicContent = content.getText().toString();
            String topicHeader = intent.getExtras().getString("topicHeader");
            String topicDescription = intent.getExtras().getString("topicDescription");
            Date topicDate = (Date) intent.getExtras().getSerializable("topicDate");
            Long userId = userServiceClient.getLoggedInUser().getId();

            Topics newTopic = new Topics();
            newTopic.setContent(topicContent);
            newTopic.setHeader(topicHeader);
            newTopic.setDescription(topicDescription);
            newTopic.setCreateDate(topicDate);
            newTopic.setUserId(userId);

            topicServiceClient.createTopic(newTopic);

            Intent goHome = new Intent(this,HomePage.class);

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("About Topic Creation");
            alertDialog.setMessage("You have successfully created your topic, Hooray!!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "HOME",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(goHome);
                        }
                    });
            alertDialog.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
