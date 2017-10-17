/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.model.Question;

import java.util.List;

/**
 * This is a Java Interface declaring Service methods
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface QuestionService {
    public void insertQuestion(Question question);
    public Question getQuestion(int pk);
    //Retrieve Multiple Question based on condition -
    public List<Question> getMultipleQuestions(int pk);
    public List<Question> getAllQuestions();
    //Update Question
    public Question updateQuestion(String questionDescription, Integer pk);
    //Delete Question
    public Question deleteQuestion(Integer pk);
}
