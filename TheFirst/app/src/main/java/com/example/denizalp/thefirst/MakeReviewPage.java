package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.ReviewServiceClient;
import com.bounswe.group7.model.Reviews;

import java.util.Date;

public class MakeReviewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_review_page);
    }

    public void submitReview(View view) throws Exception{
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        Long reviewerId = intent.getLongExtra("reviewerId",0);
        Long reviewedId = intent.getLongExtra("reviewedId",0);
        Date reviewDate = (Date) intent.getSerializableExtra("reviewDate");
        EditText editText = (EditText) findViewById(R.id.review);
        String reviewContent = editText.getText().toString();

        ReviewServiceClient reviewServiceClient = new ReviewServiceClient(token);
        Reviews newReview = new Reviews();
        newReview.setUserId(reviewedId);
        newReview.setReviewerId(reviewerId);
        newReview.setDateCreated(reviewDate);
        newReview.setText(reviewContent);

        reviewServiceClient.createReview(newReview);
        Intent goBack = new Intent(this, ReviewPage.class);
        goBack.putExtra("reviewedID", reviewedId);
        startActivity(goBack);

    }
}
