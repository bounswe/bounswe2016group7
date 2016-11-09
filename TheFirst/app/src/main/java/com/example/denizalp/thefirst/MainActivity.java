package com.example.denizalp.thefirst;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        Intent intent = new Intent(this, UserPage.class);
        try {
            LoginServiceClient loginservice = new LoginServiceClient();
            EditText editText1 = (EditText) findViewById(R.id.username);
            EditText editText2 = (EditText) findViewById(R.id.password);
            String username = editText1.getText().toString();
            String password = editText2.getText().toString();

            Users user = new Users(username, password);

            Users token = new Users();
            try {
                token = loginservice.login(user);
                intent.putExtra("nameKey", token.getFirstname());
                intent.putExtra("surnameKey", token.getLastname());
                System.out.println(token.getFirstname() + " " + token.getLastname());
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            token.getToken();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //startActivity(intent);
    }

}
