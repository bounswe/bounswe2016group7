package com.example.denizalp.thefirst;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.bounswe.group7.model.Questions;

import java.util.List;

/**
 * Created by denizalp on 30/11/16.
 */

public class QuestionNumberItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private List<Questions> quizQuestions;
    private EditText questionText;
    private EditText choiceTextA;
    private EditText choiceTextB;
    private EditText choiceTextC;
    private EditText choiceTextD;


    public QuestionNumberItemSelectedListener(List<Questions> lq,
     EditText e1, EditText e2, EditText e3, EditText e4, EditText e5
    ){
        this.quizQuestions = lq;
        this.questionText = e1;
        this.choiceTextA = e2;
        this.choiceTextB = e3;
        this.choiceTextC = e4;
        this.choiceTextD = e5;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       /* System.out.println("question "+parent.getSelectedItem()+" selected!");
        System.out.println(view.toString());
        System.out.println(parent.toString());*/
        int selectedIndex = (Integer) parent.getSelectedItem() - 1;
        if(selectedIndex >= quizQuestions.size()){
            questionText.setText("");
            choiceTextA.setText("");
            choiceTextB.setText("");
            choiceTextC.setText("");
            choiceTextD.setText("");
        }
        else {
            Questions question = quizQuestions.get(selectedIndex);
            questionText.setText(question.getQuestion());
            choiceTextA.setText(question.getChoiceA());
            choiceTextB.setText(question.getChoiceB());
            choiceTextC.setText(question.getChoiceC());
            choiceTextD.setText(question.getChoiceD());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
