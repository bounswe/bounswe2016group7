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

/*
* .commentViewpage class
* to viewing the comments of a topic
* */
public class CommentViewPage extends AppCompatActivity {

    List<Comments> commentList = new ArrayList<Comments>(); //get comments
    CommentServiceClient commentServiceClient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_view_page); //set view as activity_comment_view_page.xml under resources layout
        try{
            Intent previous = getIntent();
            Long topicId = previous.getLongExtra("topicId",-1); //the topic that we want to see the comments
            String token = previous.getStringExtra("token");
            Boolean notEmpty = previous.getBooleanExtra("notEmpty",false);
            commentServiceClient = new CommentServiceClient(token);
            commentList = commentServiceClient.getTopicComments(topicId); // fill comment list
            ListView commentListView = (ListView) findViewById(R.id.commentList); //show comment list
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
   //newComment method
//   this method is to create new comments,and available on comment view page
    public void newComment(View view){
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",-1);
        String token = previous.getStringExtra("token");

        Intent intent = new Intent(this, CommentCreatePage.class);
        intent.putExtra("topicId",topicId);
        intent.putExtra("token",token);

        startActivity(intent);
    }
}
