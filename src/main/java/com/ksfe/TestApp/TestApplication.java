package com.ksfe.TestApp;

import java.util.ArrayList;
import java.util.List;

import com.ksfe.dao.QuestionDAO;
import com.ksfe.dao.QuestionDAOImpl;
import com.ksfe.service.QuestionService;
import com.ksfe.service.QuestionServiceImpl;

public class TestApplication {
	
    public static void main(String[] args) {
    	QuestionDAO questionDAO=new QuestionDAOImpl();
    	List<Integer>questionIDList=new ArrayList<>();
    	String questionList="";
    	questionIDList.add(13);
    	questionIDList.add(14);
    	System.out.println(questionIDList);
    	QuestionService questionService = new QuestionServiceImpl();
		questionList=questionService.viewPendingQuestionList(questionIDList);
		System.out.println(questionList);
    }
}
