package com.example.denizalp.thefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.TagServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.Topics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        TagServiceClient tagServiceClient = new TagServiceClient(token);

        try
        {
            Intent intent = getIntent();
            EditText content = (EditText) findViewById(R.id.editText5);

            String topicContent = content.getText().toString();
            String topicHeader = intent.getExtras().getString("topicHeader");
            String topicDescription = intent.getExtras().getString("topicDescription");
            Date topicDate = (Date) intent.getExtras().getSerializable("topicDate");
            Long userId = userServiceClient.getLoggedInUser().getId();
            Long topicPackId = intent.getExtras().getLong("topicPackId");
            ArrayList<String> selectedTags = intent.getExtras().getStringArrayList("topicTags");
            List<Tags> tagsList = new ArrayList<Tags>();
            for(String s:selectedTags){
                Tags tag = new Tags();
                int index = s.indexOf('(');
                String label = s.substring(0,index-1);
                String description = s.substring(index);
                int refCount = 0;
                tag.setCategory(description);
                tag.setLabel(label);
                tag.setRefCount(refCount);
                Tags newTag = tagServiceClient.createTag(tag);
                tagsList.add(newTag);
                tagServiceClient.addTag(newTag);
            }

            Topics newTopic = new Topics();
            newTopic.setContent(topicContent);
            newTopic.setHeader(topicHeader);
            newTopic.setDescription(topicDescription);
            newTopic.setCreateDate(topicDate);
            newTopic.setUserId(userId);
            newTopic.setTags(tagsList);
            if(topicPackId != -1) newTopic.setTopicPackId(topicPackId);

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
