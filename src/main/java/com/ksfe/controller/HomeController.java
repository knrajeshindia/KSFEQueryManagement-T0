/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.ksfe.model.*;
import com.ksfe.service.*;
import com.ksfe.util.StringToDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is a Spring MVC based web Controller class
 *
 * @author RNarendran
 * @since 1.0,
 */

@Controller
// @RequestMapping("/")

public class HomeController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private UnitTypeService unitTypeService;
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private TargetService targetService;
    @Autowired
    private ResponseService responseService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String insertQuestion(Model model) {
        System.out.println(getClass());
        UnitType unitType = new UnitType("UnitType", "Eligibility");
        unitTypeService.insertUnitType(unitType);
        Unit unit = new Unit(100,"Password","UnitName","Code","Address","District","Manager","Email","Mobile","Telephone","Status");
        unit.setUnitType(unitType);
        Question question1 = new Question(100, "Hi How are you-1", "Remarks", 1000, "draft");
        Question question2 = new Question(100, "Hi How are you-2", "Remarks", 1000, "draft");

        Target target1=new Target(1,"status");
        Target target2=new Target(2,"status");

        Questionnaire questionnaire=new Questionnaire("QTitle", "QDesc","QRemarks", "Rajesh","Accounts Manager", 0);
        questionnaire.setPostedDate(new Date());
        questionnaire.setDueDate(StringToDate.convertString("20/10/2017"));

        Response response1=new Response(1000, "ResponseDescription", "responseRemarks", "respondentName", "respondentJobTitile", "responseStatus");
        Response response2=new Response(1000, "ResponseDescription", "responseRemarks", "respondentName", "respondentJobTitile", "responseStatus");
        response1.setResponseDate(new Date());
        response2.setResponseDate(new Date());
        question1.getResponseList().add(response1);
        question1.getResponseList().add(response2);

        questionnaire.getTargetRespondentList().add(target1);
        questionnaire.getTargetRespondentList().add(target2);
        questionnaire.getQuestionList().add(question1);
        questionnaire.getQuestionList().add(question2);

        model.addAttribute("question", question1.getQuestionDescription());
        responseService.insertResponse(response1);
        responseService.insertResponse(response2);
        unitService.insertUnit(unit);
        
        targetService.insertTarget(target1);
        targetService.insertTarget(target2);
        questionService.insertQuestion(question1);
        questionService.insertQuestion(question2);
        questionnaireService.insertQuestionnaire(questionnaire);

        questionService.getAllQuestions();
        

        return "home";
    }

}
