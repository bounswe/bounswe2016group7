package com.example.denizalp.thefirst;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        Intent intent = getIntent();
        String firstname = intent.getStringExtra("nameKey");
        String lastname = intent.getStringExtra("surnameKey");
        System.out.println(firstname+" "+lastname);
        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(firstname+" "+lastname);
    }
}
