package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.ReviewServiceClient;
import com.bounswe.group7.model.Reviews;

import java.util.Date;
/*
* MakeReviewPage class
*
* */
public class MakeReviewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_review_page);  //set view as activity_make_review.xml from resources
    }
    /*
    * submitReview method
    * this method enables to make reviews about a topic
    * */
    public void submitReview(View view) throws Exception{
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        Long reviewerId = intent.getLongExtra("reviewerId",0);  //get reviewer id
        Long reviewedId = intent.getLongExtra("reviewedId",0); //get reviewed id
        Date reviewDate = (Date) intent.getSerializableExtra("reviewDate");
        EditText editText = (EditText) findViewById(R.id.review);
        String reviewContent = editText.getText().toString();

        ReviewServiceClient reviewServiceClient = new ReviewServiceClient(token);
        Reviews newReview = new Reviews();
        newReview.setUserId(reviewedId); //set user id as reviewed id
        newReview.setReviewerId(reviewerId);  //set reviewer id as reviewer id
        newReview.setDateCreated(reviewDate);  //set date
        newReview.setText(reviewContent);  //set content

        reviewServiceClient.createReview(newReview); //create review by using the above info
        Intent goBack = new Intent(this, ReviewPage.class);  //go ReviewPage class
        goBack.putExtra("reviewedID", reviewedId);
        startActivity(goBack);

    }
}
