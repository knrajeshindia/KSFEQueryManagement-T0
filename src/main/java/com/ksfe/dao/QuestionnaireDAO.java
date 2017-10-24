package com.ksfe.dao;

import com.ksfe.model.Questionnaire;

public interface QuestionnaireDAO {

	public Questionnaire insertQuestionnaire(Questionnaire questionnaire) ;


    //Retrieve one Questionnaire
    Questionnaire getQuestionnaire(int pk);

    Questionnaire updateQuestionnaire(Questionnaire questionnaire, Integer pk);
}
