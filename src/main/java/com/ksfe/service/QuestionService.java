/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.model.Question;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This is a Java Interface declaring Service methods
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface QuestionService {
    public void insertQuestion(Question question);
    public List<Question> getAllQuestions();
    public Question getQuestion(int pk);
}
