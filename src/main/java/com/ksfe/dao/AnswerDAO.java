package com.ksfe.dao;

import com.ksfe.model.Answer;

import java.util.ArrayList;

public interface AnswerDAO {

	public void insertAnswer(Answer answer) ;


    ArrayList<Answer> saveAnswerList(ArrayList<Answer> answerList);
}
