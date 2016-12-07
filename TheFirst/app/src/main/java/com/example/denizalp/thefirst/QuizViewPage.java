package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.bounswe.group7.api.client.QuizServiceClient;
import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuizes;

import java.util.ArrayList;

public class QuizViewPage extends AppCompatActivity {

    Quizes quiz = new Quizes();
    QuizServiceClient quizServiceClient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view_page);
        try {
            Intent intent = getIntent();
            Long topicId = intent.getLongExtra("topicId", -1);
            String token = intent.getStringExtra("token");
            System.out.println(token);
            quizServiceClient = new QuizServiceClient(token);
            quiz = quizServiceClient.getQuiz(topicId);
            ListView quizView = (ListView) findViewById(R.id.quizList);
            QuizAdapter quizAdapter = new QuizAdapter(this, quiz);
            quizView.setAdapter(quizAdapter);
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void evaluate(View view) throws Exception{
        ListView quizView = (ListView) findViewById(R.id.quizList);
        QuizAdapter quizAdapter = (QuizAdapter) quizView.getAdapter();
        ArrayList<Integer> checkedList = quizAdapter.getCheckedList();
        Quizes quizes = quiz;
        for(int i=0; i<quizes.getQuestions().size(); i++)
        {
            Integer checkedID = checkedList.get(i);
            View questionView = quizAdapter.getView(i,null,quizView);
            Questions questions = quizAdapter.getItem(i);
            //RadioGroup radioGroup = (RadioGroup) questionView.findViewById(R.id.choices);
            //int checked = radioGroup.getCheckedRadioButtonId();
            //View button = radioGroup.findViewById(checked);
            char chosenAnswer =' ';
            switch (checkedID){
                case R.id.choiceA: chosenAnswer = 'A';
                    break;

                case R.id.choiceB: chosenAnswer = 'B';
                    break;

                case R.id.choiceC: chosenAnswer = 'C';
                    break;

                case R.id.choiceD: chosenAnswer = 'D';
                    break;
            }
            questions.setChosenAnswer(chosenAnswer);
        }
        SolvedQuizes result = quizServiceClient.solveQuiz(quiz);
        System.out.println(result.getScore());
    }
}
