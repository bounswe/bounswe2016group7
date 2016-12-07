package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;

import java.util.ArrayList;
import java.util.List;

import static com.example.denizalp.thefirst.R.id.choiceA;

/**
 * Created by denizalp on 29/11/16.
 */

public class QuizAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Quizes mQuiz;
    private ArrayList<Integer> checkedList = new ArrayList<Integer>();

    public QuizAdapter(Activity activity, Quizes quiz){
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mQuiz = quiz;
    }

    @Override
    public int getCount() {
        return mQuiz.getQuestions().size();
    }

    @Override
    public Questions getItem(int position) {
        return mQuiz.getQuestions().get(position);
    }

    @Override
    public long getItemId(int position) {
        return mQuiz.getQuestions().get(position).getQuestionId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View quizView;
        quizView = mInflater.inflate(R.layout.activity_quiz_question_view, null);
        TextView quizQuestion = (TextView) quizView.findViewById(R.id.quizQuestion);
        RadioGroup radioGroup = (RadioGroup) quizView.findViewById(R.id.choices);
        RadioButton choiceA = (RadioButton) quizView.findViewById(R.id.choiceA);
        RadioButton choiceB = (RadioButton) quizView.findViewById(R.id.choiceB);
        RadioButton choiceC = (RadioButton) quizView.findViewById(R.id.choiceC);
        RadioButton choiceD = (RadioButton) quizView.findViewById(R.id.choiceD);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View button = radioGroup.findViewById(checkedId);
                        int checkedID = 0;
                        if(button != null) {
                            checkedID = button.getId();
                            checkedList.add(position,checkedID);
                            //checkId = checkedID;
                            //System.out.println(checkedID);
                        }
                    }
                }
        );
        Questions question = mQuiz.getQuestions().get(position);
        quizQuestion.setText(question.getQuestion());
        choiceA.setText(question.getChoiceA());
        choiceB.setText(question.getChoiceB());
        choiceC.setText(question.getChoiceC());
        choiceD.setText(question.getChoiceD());

        return quizView;
    }

    public ArrayList<Integer> getCheckedList(){
        return checkedList;
    }
 }
