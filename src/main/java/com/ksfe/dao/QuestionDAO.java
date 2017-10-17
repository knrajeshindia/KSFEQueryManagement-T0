/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Question;

import java.util.List;

/**
 * This is a Java interface for defining DAO operations
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface QuestionDAO {
    //insert Question
    void insertQuestion(Question question);
    //Retrieve all Question
    List<Question> getAllQuestions();
    //Retrieve one Question
    Question getQuestion(int pk );

    //Retrieve Multiple Question based on condition -
    List<Question> getMultipleQuestions(int pk);

    //Update Question
    Question updateQuestion(String questionDescription, Integer pk);

    //Delete Question
    Question deleteQuestion(Integer pk);
}
