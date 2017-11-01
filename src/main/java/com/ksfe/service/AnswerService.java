/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.model.Answer;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is a Java Interface declaring Service methods
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface AnswerService {
    public void insertAnswer(Answer answer);

    String insertAnswer(ArrayList<Answer> answerList);

    void updateAnswerList(Collection<Integer> answerIDList, Integer responseID);

    //Update Answers - Publish
    @Transactional
    String updateAnswerList(List<Answer> answerList);

    String getAnswerList(int responseID);
}
