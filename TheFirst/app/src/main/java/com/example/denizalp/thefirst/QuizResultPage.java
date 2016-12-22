package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bounswe.group7.api.client.QuizServiceClient;
import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.SolvedQuestions;
import com.bounswe.group7.model.SolvedQuizes;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizResultPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result_page);
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",-1);
        String token = previous.getStringExtra("token");
        ArrayList<Integer> optionOrder = previous.getIntegerArrayListExtra("optionOrder");
        QuizServiceClient quizServiceClient = new QuizServiceClient(token);
        try{
            Quizes quiz = quizServiceClient.getQuiz(topicId);
            for(int i=0; i<quiz.getQuestions().size(); i++){
                int option = optionOrder.get(i);
                Questions questions = quiz.getQuestions().get(i);
                char chosenAnswer =' ';
                switch (option){
                    case 1:
                        chosenAnswer = 'A';
                        break;

                    case 2:
                        chosenAnswer = 'B';
                        break;

                    case 3:
                        chosenAnswer = 'C';
                        break;

                    case 4:
                        chosenAnswer = 'D';
                        break;
                }
                questions.setChosenAnswer(chosenAnswer);
            }
            SolvedQuizes result = quizServiceClient.solveQuiz(quiz);
            TextView textView = (TextView) findViewById(R.id.textView21);
            String score = String.format("Your score is %.2f",result.getScore());
            textView.setText(score);

            List<SolvedQuestions> solvedQuestions = result.getSolvedQuestions();
            ListView quizResultView = (ListView) findViewById(R.id.quizResultView);
            SolvedQuestionAdapter adapter = new SolvedQuestionAdapter(this, result, quiz);
            quizResultView.setAdapter(adapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void backToTopic(View v){
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",-1);
        Intent intent = new Intent(this, ShowTopicPage.class);
        intent.putExtra("topicId",topicId);
        startActivity(intent);
    }
}
