package com.example.denizalp.thefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ObbInfo;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Users;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.clear();
        prefEditor.apply();
        TopicServiceClient tsc = new TopicServiceClient();
        try {
            System.out.println(tsc.getRecentTopics().size());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loginUser(View view){
        Intent intent = new Intent(this, UserPage.class);
        Bundle bundle = new Bundle();
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

                if(token != null) {
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    // System.out.println(token.getFirstname() + " " + token.getLastname());
                    startActivity(intent);
                }
                else {
                    System.out.println("Username or password is incorrect!!!");
                }
            } catch (Exception e) {
                //System.out.println("Username or password is incorrect!!!");
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Username or password is incorrect!!!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                e.printStackTrace();
            }
            //token.getToken();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //startActivity(intent);
    }

    public void getInfo(View view) {
        //move to obtain info page
        Intent intent = new Intent(this, ObtainInfo.class);
        startActivity(intent);
    }

}
