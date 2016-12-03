package com.example.denizalp.thefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.model.security.Authority;
import com.bounswe.group7.model.security.AuthorityName;

import java.util.ArrayList;
import java.util.List;

public class ObtainInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtain_info);
        //get information
        //LoginServiceClient loginServiceClient = new LoginServiceClient();
        //Users user = new Users();

    }

    public void registerUser(View view) {
        //obtain strings from UIObjects (EditText)
        Intent loginIntent = new Intent(this, MainActivity.class);
        EditText eTextUsername = (EditText) findViewById(R.id.username);
        EditText eTextEmail = (EditText) findViewById(R.id.email);
        EditText eTextPassword = (EditText) findViewById(R.id.password1);
        EditText eTextFirstname = (EditText) findViewById(R.id.firstname);
        EditText eTextLastname = (EditText) findViewById(R.id.lastname);
        EditText eTextRePassword = (EditText) findViewById(R.id.password2);
        RadioGroup radioGender = (RadioGroup) findViewById(R.id.gender);
        RadioGroup radioAuthority = (RadioGroup) findViewById(R.id.authority);
        String username = eTextUsername.getText().toString();
        String email = eTextEmail.getText().toString();
        String password = eTextPassword.getText().toString();
        String repassword = eTextRePassword.getText().toString();
        String firstname = eTextFirstname.getText().toString();
        String lastname = eTextLastname.getText().toString();
        int genderButtonID = radioGender.getCheckedRadioButtonId();
        RadioButton genderButton = (RadioButton) radioGender.findViewById(genderButtonID);
        String gender = genderButton.getText().toString().substring(0,1);

        int autButtonID = radioAuthority.getCheckedRadioButtonId();
        RadioButton autButton = (RadioButton) radioAuthority.findViewById(autButtonID);
        String author = autButton.getText().toString();
        AuthorityName authorityName = AuthorityName.ROLE_EXPLORER;
        List<Authority> authorityList = new ArrayList<Authority>();
        Authority authority = new Authority((long) authorityName.getId(),authorityName);
        authorityList.add(authority);
        if(author.equals("Creator")){
            authorityName = AuthorityName.ROLE_CREATOR;
            authority = new Authority((long) authorityName.getId(), authorityName);
            authorityList.add(authority);
        }
        else {
            System.out.println("girmiyor ki zaa");
        }

        if(password.equals(repassword)) {
            LoginServiceClient logClient = new LoginServiceClient();
            Users token = new Users();
            try {
                Users newUser = new Users(username, password);
                newUser.setEmail(email);
                newUser.setFirstname(firstname);
                newUser.setLastname(lastname);
                newUser.setGender(gender);
                newUser.setAuthorities(authorityList);
                //System.out.println(authorityList.get(0).getName().getName()+","+authorityList.get(1).getName().getName());
                //System.out.println(newUser.toString());
                token = logClient.register(newUser);
                //token.setGender(gender);
                //token.setAuthorities(authorityList);
                //alert box to show login successful and divert to login page
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Approval");
                alertDialog.setMessage("You have successfully registered, Welcome!!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "HOME",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(loginIntent);
                            }
                        });
                alertDialog.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            //dont know what to do
        }
    }
}
