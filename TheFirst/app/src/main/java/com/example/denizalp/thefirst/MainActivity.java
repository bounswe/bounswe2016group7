package com.example.denizalp.thefirst;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getInfo(View view){
        //Intent intent = new Intent(this, ObtainInfo.class);
        //startActivity(intent);

        try {
            LoginServiceClient loginservice = new LoginServiceClient();
            //put username - password here
            Users user = new Users("ugurbor", "password");

            Users token = new Users();
            try {
                token = loginservice.login(user);
                System.out.println("successful " + token.getToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
            token.getToken();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
