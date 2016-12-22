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
import com.bounswe.group7.model.SolvedQuestions;
import com.bounswe.group7.model.SolvedQuizes;

import java.util.List;

/**
 * Created by denizalp on 22/12/16.
 */

public class SolvedQuestionAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private SolvedQuizes solvedQuizes;
    private Quizes quizes;

    public SolvedQuestionAdapter(Activity activity, SolvedQuizes solvedQuizes, Quizes quizes){
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.solvedQuizes = solvedQuizes;
        this.quizes = quizes;
    }
    @Override
    public int getCount() {
        return solvedQuizes.getSolvedQuestions().size();
    }

    @Override
    public Object getItem(int position) {
        return solvedQuizes.getSolvedQuestions().get(position);
    }

    @Override
    public long getItemId(int position) {
        return solvedQuizes.getSolvedQuestions().get(position).getQuestionId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View quizResultView = mInflater.inflate(R.layout.activity_quiz_question_result, null);
        TextView quizQ = (TextView) quizResultView.findViewById(R.id.quizQuestionn);
        RadioGroup radioGroup = (RadioGroup) quizResultView.findViewById(R.id.choicess);
        RadioButton choiceA = (RadioButton) quizResultView.findViewById(R.id.choiceAA);
        RadioButton choiceB = (RadioButton) quizResultView.findViewById(R.id.choiceBB);
        RadioButton choiceC = (RadioButton) quizResultView.findViewById(R.id.choiceCC);
        RadioButton choiceD = (RadioButton) quizResultView.findViewById(R.id.choiceDD);
        TextView explanation = (TextView) quizResultView.findViewById(R.id.questionExplanation);
        SolvedQuestions solvedQuestion = (SolvedQuestions) getItem(position);
        Questions question = quizes.getQuestions().get(position);

        quizQ.setText(question.getQuestion());
        if(!question.getChoiceA().equals("Default_Choice")) choiceA.setText(question.getChoiceA());
        else choiceA.setVisibility(RadioButton.GONE);

        if(!question.getChoiceB().equals("Default_Choice")) choiceB.setText(question.getChoiceB());
        else choiceB.setVisibility(RadioButton.GONE);

        if(!question.getChoiceC().equals("Default_Choice")) choiceC.setText(question.getChoiceC());
        else choiceC.setVisibility(RadioButton.GONE);

        if(!question.getChoiceD().equals("Default_Choice")) choiceD.setText(question.getChoiceD());
        else choiceD.setVisibility(RadioButton.GONE);

        char selected = solvedQuestion.getChosenAnswer();
        switch(selected){
            case 'A':
                choiceA.setChecked(true);
                choiceB.setEnabled(false);
                choiceC.setEnabled(false);
                choiceD.setEnabled(false);
            break;

            case 'B':
                choiceB.setChecked(true);
                choiceA.setEnabled(false);
                choiceC.setEnabled(false);
                choiceD.setEnabled(false);
            break;

            case 'C':
                choiceC.setChecked(true);
                choiceB.setEnabled(false);
                choiceA.setEnabled(false);
                choiceD.setEnabled(false);
            break;

            case 'D':
                choiceD.setChecked(true);
                choiceB.setEnabled(false);
                choiceC.setEnabled(false);
                choiceA.setEnabled(false);
            break;
        }
        String explain = "";
        if(solvedQuestion.isTrueOrFalse()){
           explain = "That is correct!";
        }
        else{
           explain = "False, the correct answer is "+solvedQuestion.getRightAnswer()+"";
        }
        explanation.setText(explain);
        return quizResultView;
    }
}
