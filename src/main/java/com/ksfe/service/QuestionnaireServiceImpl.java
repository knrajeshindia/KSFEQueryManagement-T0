/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.QuestionnaireDAO;
import com.ksfe.model.Questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
/**
 * This is a Spring Service class - for implementing Service requirements
 *
 * @author RNarendran
 * @since 1.0,
 */

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
	private QuestionnaireDAO questionnaireDAO;

    @Transactional
    public void insertQuestionnaire(Questionnaire questionnaire) {
        System.out.println(getClass());
        questionnaireDAO.insertQuestionnaire(questionnaire);
    }

}
