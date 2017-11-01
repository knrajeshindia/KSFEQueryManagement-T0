package com.ksfe.dao;

import com.ksfe.model.Questionnaire;

import java.util.List;

public interface QuestionnaireDAO {

	public Questionnaire insertQuestionnaire(Questionnaire questionnaire) ;


    //Retrieve one Questionnaire
    Questionnaire getQuestionnaire(int pk);

    Questionnaire updateQuestionnaire(List<Integer> questionnaire, Integer pk);

    List<Questionnaire> viewPendingQuestionnaireList(Integer userID);

    List<Questionnaire> viewCompleteQuestionnaireList(Integer unitID);
}
