package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.Users;

import java.util.Date;

/*
* CommentCreatePage class
* Add comment to a topic
* */
public class CommentCreatePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_create_page); //set layout as activity_comment_create_page
    }
//addCmment method
//to add comment to a topic
    public void addComment(View v) throws Exception{
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",0);  //get topic id
        String token = previous.getStringExtra("token"); //get token
        EditText editText = (EditText) findViewById(R.id.editComment);

        CommentServiceClient commentServiceClient = new CommentServiceClient(token); //create new comment service client
        UserServiceClient userServiceClient = new UserServiceClient(token);  //create new user service client
        Users user = userServiceClient.getLoggedInUser();

        Comments theComment = new Comments();
        theComment.setTopicId(topicId);
        theComment.setRate(0);
        theComment.setDateCreated(new Date());
        theComment.setUserId(user.getId());
        theComment.setText(editText.getText().toString());

        Comments created = commentServiceClient.createComment(theComment);
        if(created != null){
            Intent intent = new Intent(this, CommentViewPage.class);  //gather this and form as comment view page
            intent.putExtra("topicId",created.getTopicId());
            intent.putExtra("notEmpty",true);
            intent.putExtra("token",token);
            startActivity(intent);
        }

    }
}
