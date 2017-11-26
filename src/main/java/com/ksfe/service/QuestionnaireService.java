/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.model.Questionnaire;
import java.util.List;

/**
 * This is a Java Interface declaring Service methods
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface QuestionnaireService {
    public String insertQuestionnaire(Questionnaire questionnaire);
    //Update Question
    public Questionnaire updateQuestionnaire(List<Integer> questionnaire, Integer pk);

    String viewPendingQuestionnaireList(Integer userID);

    String viewCompleteQuestionnaireList(Integer unitID);

    String viewMyQuestionnaireList(Integer unitID);
}
