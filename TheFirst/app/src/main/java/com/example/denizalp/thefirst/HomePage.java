package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        //System.out.println("tokenım burada: "+token);
    }

    public void showRecentTopics(View view){
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("recentOrTop",1);
        startActivity(intent);
    }

    public void showTopTopics(View view){
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("recentOrTop",2);
        startActivity(intent);
    }
}
