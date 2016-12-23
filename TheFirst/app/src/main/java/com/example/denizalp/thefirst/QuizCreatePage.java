package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.bounswe.group7.api.client.QuizServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
* QuizCreatePage class
* This method is for authors to add quizzes to ther topics.
* */
public class QuizCreatePage extends AppCompatActivity {

    List<Questions> quizQuestions = new ArrayList<Questions>();  //list of questions
    List<Integer> questionNumbers = new ArrayList<Integer>();  //list of question numbers
    int questionNumber = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_create_page); //set view activity_quiz_create_page.xml from resources
        Spinner spinnerAnswers = (Spinner) findViewById(R.id.sAnswer);
        List<Character> answers = new ArrayList<Character>();
        answers.add('A'); answers.add('B'); answers.add('C'); answers.add('D');
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(
                this, android.R.layout.simple_spinner_item, answers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnswers.setAdapter(adapter);
        Spinner spinnerQNumbers = (Spinner) findViewById(R.id.sQuestionNumber);
        //questionNumbers.add(1);
        //questionNumbers.add(2);
        questionNumbers.add(questionNumber);  //add question number to the list
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, questionNumbers);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQNumbers.setAdapter(adapter2);
        //spinnerQNumbers.setOnItemSelectedListener(new QuestionNumberItemSelectedListener());
    }
  //add question method enables to add new questions
    public void addQuestion(View v){
        EditText questionText = (EditText) findViewById(R.id.quizQ);
        EditText choiceTextA = (EditText) findViewById(R.id.optionA);
        EditText choiceTextB = (EditText) findViewById(R.id.optionB);
        EditText choiceTextC = (EditText) findViewById(R.id.optionC);
        EditText choiceTextD = (EditText) findViewById(R.id.optionD);
        Spinner spinnerAnswers = (Spinner) findViewById(R.id.sAnswer);
        Spinner spinnerQNumbers = (Spinner) findViewById(R.id.sQuestionNumber);

        String question = questionText.getText().toString();
        String choiceA = choiceTextA.getText().toString();
        String choiceB = choiceTextB.getText().toString();
        String choiceC = choiceTextC.getText().toString();
        String choiceD = choiceTextD.getText().toString();
        char trueAnswer = (Character) spinnerAnswers.getSelectedItem();
        questionNumber = (Integer) spinnerQNumbers.getSelectedItem();

        Questions aQuestion = new Questions();
        aQuestion.setQuestion(question);
        aQuestion.setChoiceA(choiceA);
        aQuestion.setChoiceB(choiceB);
        aQuestion.setChoiceC(choiceC);
        aQuestion.setChoiceD(choiceD);
        aQuestion.setRightAnswer(trueAnswer);
        aQuestion.setDateCreated(new Date());

        quizQuestions.add(questionNumber-1, aQuestion);
        questionNumber++;
        questionNumbers.add(questionNumber);
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, questionNumbers);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQNumbers.setAdapter(adapter2);
        spinnerQNumbers.setOnItemSelectedListener(new QuestionNumberItemSelectedListener(
                quizQuestions,
                questionText,
                choiceTextA,
                choiceTextB,
                choiceTextC,
                choiceTextD
        ));
    }

    public void createQuiz(View v) throws Exception{
        List<Questions> readyQuestions = quizQuestions;
        Intent previous = getIntent();
        Long topicId = previous.getLongExtra("topicId",0);
        String token = previous.getStringExtra("token");
        System.out.println(token);
        QuizServiceClient quizServiceClient = new QuizServiceClient(token);
        UserServiceClient userServiceClient = new UserServiceClient(token);
        Long userId = userServiceClient.getLoggedInUser().getId();

        Quizes newQuiz = new Quizes();

        newQuiz.setCreateDate(new Date());
        newQuiz.setName("Quiz");

        System.out.println(topicId);
        newQuiz.setTopicId(topicId);

        //newQuiz.setQuestions(readyQuestions);

        System.out.println(userId);
        newQuiz.setUserId(userId);

        Quizes theQuiz = quizServiceClient.createQuiz(newQuiz);
        for(Questions q: readyQuestions){
            q.setQuizId(theQuiz.getQuizId());
            quizServiceClient.addQuestion(q);
        }

        if(theQuiz != null) {
            System.out.println("you have created a quiz!");
            Intent intent = new Intent(this, ShowTopicPage.class);
            intent.putExtra("topicId", topicId);
            startActivity(intent);
        }
        else {
            System.out.println("olmadı hocam");
        }
    }
}
