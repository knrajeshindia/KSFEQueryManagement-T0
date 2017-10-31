package com.ksfe.dao;

import com.ksfe.model.Answer;

import java.util.ArrayList;
import java.util.Collection;

public interface AnswerDAO {

	public void insertAnswer(Answer answer) ;


    ArrayList<Answer> saveAnswerList(ArrayList<Answer> answerList);

    //Retrieve one Answer
    Answer getAnswer(int pk);

    void updateAnswerList(Collection<Integer> answerIDList, Integer responseID);
}
