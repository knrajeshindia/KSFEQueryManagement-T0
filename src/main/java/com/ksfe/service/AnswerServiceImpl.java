/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.AnswerDAO;
import com.ksfe.model.Answer;
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
public class AnswerServiceImpl implements AnswerService {
    @Autowired
	private AnswerDAO answerDAO;

    @Transactional
    public void insertAnswer(Answer answer) {
        System.out.println(getClass());
        answerDAO.insertAnswer(answer);
    }
}
