package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.TopicPacks;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.glassfish.hk2.api.messaging.Topic;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* This class is the TopicPage class which initiates the Topic page
*
* */
public class TopicPage extends AppCompatActivity {
    /*
    * Topic Pack List holds the list of Topic packages
    * topicPackNames holds the names of topic packages as Strings
    * */
    List<TopicPacks> topicPacksList = new ArrayList<TopicPacks>();
    List<String> topicPackNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view from activity_topic_page_2.xml
        setContentView(R.layout.activity_topic_page_2);
       /* Spinner topicPackSpinner = (Spinner) findViewById(R.id.spinnerTopicPack);
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        UserServiceClient userServiceClient = new UserServiceClient(token);
        TopicServiceClient topicServiceClient = new TopicServiceClient(token);
        try {
            Long userId = userServiceClient.getLoggedInUser().getId();
            topicPacksList = topicServiceClient.getUserTopicPacks(userId);
            //List<String> topicPackNames = new ArrayList<String>();
            if(!topicPacksList.isEmpty()){
                for(int i=0; i<topicPacksList.size(); i++){
                    topicPackNames.add(i, topicPacksList.get(i).getName());
                }
                topicPackNames.add(topicPacksList.size(),"Not selected");
            }
            else topicPackNames.add(0,"Not Selected");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, topicPackNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            topicPackSpinner.setAdapter(adapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        */
    }
    /*
     *goEditing method
     * This method enables Topic editing feature
     */
    public void goEditing(View v){
        EditText header = (EditText) findViewById(R.id.editText2);
        EditText description = (EditText) findViewById(R.id.editText);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTopicPack);
        Date createDate = new Date();

        String topicHeader = header.getText().toString();
        String topicDescription = description.getText().toString();
        String topicPackName = (String) spinner.getSelectedItem();
        Long topicPackId = (long) -1;
        //if a topic page is selected
        if(!topicPackName.equalsIgnoreCase("Not Selected")) {
            //get the index of the topic from the topicPackNames
            int index = topicPackNames.indexOf(topicPackName);
            TopicPacks topicPacks = topicPacksList.get(index);
            topicPackId = topicPacks.getTopicPackId();
        }
       //create new buddle
        Bundle topicBundle = new Bundle();
        topicBundle.putString("topicHeader",topicHeader);  //set topicheader
        topicBundle.putString("topicDescription",topicDescription);   //set topicDescription
        topicBundle.putSerializable("topicDate",createDate);  //set topicDate
        topicBundle.putLong("topicPackId",topicPackId);   //set topicPackId
        topicBundle.putStringArrayList("topicTags",allSelected);  //set topicTags

        Intent intent = new Intent(this, TopicAddMaterialQuiz.class);
        intent.putExtras(topicBundle);
        startActivity(intent);
    }
   //listSemanticResult method

    public void listSemanticResult(View v){
        EditText editText = (EditText) findViewById(R.id.editText14);
        Spinner semanticSpinner = (Spinner) findViewById(R.id.spinnerSemantic);
        String param = editText.getText().toString();
        String url = "https://www.wikidata.org/w/api.php?action=wbsearchentities&search=" + param +
                "&language=en&format=json&callback=?'";
        List<String> semanticResults = getSemanticResults(url);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                semanticResults);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semanticSpinner.setAdapter(adapter);
        //System.out.println(url);
        //Intent intent = new Intent(this, SemanticTagResultsView.class);
       // intent.putExtra("url", url);
       // startActivity(intent);
    }

    List<String> allTags = new ArrayList<String>();  //holds the tags
    ArrayList<String> allSelected = new ArrayList<String>();

   /*
   * addTag method
   * this method enables putting tags to topic
   * */
    public void addTag(View v){
        Spinner semanticSpinner = (Spinner) findViewById(R.id.spinnerSemantic);
        //TextView tagView = (TextView) findViewById(R.id.tags);
        String selected = (String) semanticSpinner.getSelectedItem();
        allSelected.add(selected);
        int indexOfSpace = selected.indexOf('(');
        String label = selected.substring(0,indexOfSpace);
        allTags.add(label);
        GridView gridView = (GridView) findViewById(R.id.tagGridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,allTags);
        gridView.setAdapter(adapter);
        //tagView.setText(allTags);
    }

    //getSemanticResults method
    public List<String> getSemanticResults(String url) {
        List<String> results = new ArrayList<String>();
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            String output = response.toString();
            //System.out.println(output.substring(6,output.length()-1));
            output = output.substring(6,output.length()-1);
            JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
            //System.out.println(jsonObject.toString());
            JsonArray jsonArray = jsonObject.getAsJsonArray("search");
            //System.out.println(jsonArray.toString());
            int arrSize = jsonArray.size();
            for(int i=0; i<arrSize; i++){
                JsonElement element = jsonArray.get(i);
                String s = "";
                s += element.getAsJsonObject().getAsJsonPrimitive("label").getAsString();
                s += " ";
                s += "(";
                s += element.getAsJsonObject().getAsJsonPrimitive("description").getAsString();
                s += ")";
                results.add(i,s);
               // System.out.print(element.getAsJsonObject().getAsJsonPrimitive("label").getAsString()+": ");
               // System.out.print(element.getAsJsonObject().getAsJsonPrimitive("description").getAsString()+"\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return results;
    }
}
