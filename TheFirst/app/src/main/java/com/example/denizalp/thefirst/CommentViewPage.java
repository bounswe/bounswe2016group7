package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.model.Comments;

import java.util.ArrayList;
import java.util.List;

public class CommentViewPage extends AppCompatActivity {

    List<Comments> commentList = new ArrayList<Comments>();
    CommentServiceClient commentServiceClient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_view_page);
        try{
            Intent previous = getIntent();
            Long topicId = previous.getLongExtra("topicId",-1);
            String token = previous.getStringExtra("token");
            Boolean notEmpty = previous.getBooleanExtra("notEmpty",false);
            commentServiceClient = new CommentServiceClient(token);
            commentList = commentServiceClient.getTopicComments(topicId);
            ListView commentListView = (ListView) findViewById(R.id.commentList);
            if(notEmpty) {
                CommentAdapter commentAdapter = new CommentAdapter(this, commentList, token);
                commentListView.setAdapter(commentAdapter);
                commentListView.setClickable(true);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void newComment(View view){
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",-1);
        String token = previous.getStringExtra("token");

        Intent intent = new Intent(this, CommentCreatePage.class);
        intent.putExtra("topicId",topicId);
        intent.putExtra("token",token);

        startActivity(intent);
    }

    public void homePage(View v){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void profilePage(View v){
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }
}
