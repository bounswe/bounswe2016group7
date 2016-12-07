package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.QuizServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.model.VotedComments;

import java.util.ArrayList;
import java.util.List;

public class ShowTopicPage extends AppCompatActivity {

    TopicServiceClient topicServiceClient = null;
    UserServiceClient userServiceClient = null;
    Long topicCreatorId = null;
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
            boolean isFollowed = topicServiceClient.checkFollowedTopic(topicId);
            Button follow = (Button) findViewById(R.id.followButton);
            if(isFollowed) follow.setText("√ Following");
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
            TextView topicPack = (TextView) findViewById(R.id.topicPackName);
            EditText topicDescription = (EditText) findViewById(R.id.topicDescription);
            topicDescription.setKeyListener(null);
            TextView topicRate = (TextView) findViewById(R.id.topicRate);
            EditText topicContent = (EditText) findViewById(R.id.topicContent);
            topicContent.setKeyListener(null);
            ListView tagView = (ListView) findViewById(R.id.tagListView);
            Button followButton = (Button) findViewById(R.id.followButton);
            followButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    try {
                        if (!topicServiceClient.checkFollowedTopic(t.getTopicId())) {
                            topicServiceClient.followTopic(t.getTopicId());
                            followButton.setText("√ Following");
                        }
                        else {
                            topicServiceClient.unfollowTopic(t.getTopicId());
                            followButton.setText("+ Follow");
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            );

            try {
                Users users = userServiceClient.getUser(t.getUserId());
                String topicPackName = topicServiceClient.getTopicPack(t.getTopicPackId()).getName();
                topicCreatorId = t.getUserId();

                System.out.println(t.getHeader());
                topicHeader.setText(t.getHeader());

                System.out.println(users.getUsername());
                topicCreator.setText(users.getUsername());

                System.out.println(t.getCreateDate().toString());
                topicDate.setText(t.getCreateDate().toString());

                System.out.println(t.getDescription());
                topicDescription.setText(t.getDescription());

                String rate = "Rate: " + t.getRate();
                System.out.println(rate);
                topicRate.setText(rate);

                System.out.println(t.getContent());
                topicContent.setText(t.getContent());

                System.out.println(topicPackName);
                topicPack.setText(topicPackName);

                List<Tags> tagsList = t.getTags();
                ArrayList<String> tagStringList = new ArrayList<String>();
                for(Tags tag:tagsList){
                    tagStringList.add(tag.getLabel()+" "+tag.getCategory());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, tagStringList);
                tagView.setAdapter(adapter);

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

    public void goCreatorPage(View v) throws Exception{
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        topicServiceClient = new TopicServiceClient(token);
        userServiceClient = new UserServiceClient(token);
        Intent intent = getIntent();
        Long topicId = intent.getLongExtra("topicId",0);
        Topics topic = null;
        try{
            topic = topicServiceClient.getTopic(topicId);
            Long creatorId = topic.getUserId();
            Intent goCreatorProfile = new Intent(this,UserPage.class);
            goCreatorProfile.putExtra("creatorID",creatorId);
            startActivity(goCreatorProfile);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showQuiz(View view) throws Exception{
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        QuizServiceClient quizServiceClient = new QuizServiceClient(token);
        Intent getTopicID = getIntent();
        Long topicId = getTopicID.getLongExtra("topicId",0);
        Quizes quizes = quizServiceClient.getQuiz(topicId);
        Long userId = userServiceClient.getLoggedInUser().getId();
        if(quizes != null)
        {
            Intent intent = new Intent(this, QuizViewPage.class);
            intent.putExtra("topicId", topicId);
            intent.putExtra("token", token);
            startActivity(intent);
        }
        else{
            // go to empty quiz page and put a button "add quiz"
            Intent intent = new Intent(this, EmptyQuizPage.class);
            intent.putExtra("topicId", topicId);
            intent.putExtra("token", token);
            intent.putExtra("userId",userId);
            intent.putExtra("creatorId",topicCreatorId);
            startActivity(intent);
        }
    }

    public void rateTopic(View v){
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",0);

        Intent intent = new Intent(this, RateTopicPage.class);
        intent.putExtra("topicId",topicId);
        startActivity(intent);
    }

    public void showDiscussion(View v) throws Exception{
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        CommentServiceClient commentServiceClient = new CommentServiceClient(token);
        Intent getTopicID = getIntent();
        Long topicId = getTopicID.getLongExtra("topicId",0);
        List<Comments> commentList = commentServiceClient.getTopicComments(topicId);
        if(commentList != null)
        {
            Intent intent = new Intent(this, CommentViewPage.class);
            intent.putExtra("topicId", topicId);
            intent.putExtra("token", token);
            intent.putExtra("notEmpty", true);
            startActivity(intent);
        }
        else{
            // go to empty quiz page and put a button "add quiz"
            Intent intent = new Intent(this, CommentViewPage.class);
            intent.putExtra("topicId", topicId);
            intent.putExtra("token", token);
            intent.putExtra("notEmpty",false);
            startActivity(intent);
        }
    }

}
