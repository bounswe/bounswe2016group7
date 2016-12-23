package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SearchView;

public class SearchTopicPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_topic_page);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String currentToken = sharedPref.getString("currentToken","");
        Activity activity = this;
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();     // Close keyboard on pressing IME_ACTION_SEARCH option
                //Log.d(TAG, "QueryTextSubmit : "+ query);
                //load search query
                //System.out.println("You have looked for "+query);
                Intent intent = new Intent(activity,TopicListPage.class);
                intent.putExtra("token",currentToken);
                intent.putExtra("option",5);
                intent.putExtra("keyword",query);
                startActivity(intent);
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                //Log.d(TAG, "QueryTextChange: "+ newText);
                //System.out.println("You have looked now for "+newText);
                return false;
            }
        });
    }
}
