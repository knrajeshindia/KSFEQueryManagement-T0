/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ksfe.model.*;
import com.ksfe.service.*;
import com.ksfe.util.StringToDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    Target target1;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");   
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


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
        
        Unit unit = new Unit(100, "Password", "UnitName", "Code", "Address", "District", "Manager", "Email", "Mobile", "Telephone", "Status");
        unit.setUnitType(unitType);
        Question question1 = new Question(1, "Hi How are you", "Remarks", 1, "draft");

         target1= new Target(1, "status");


        Questionnaire questionnaire = new Questionnaire("QTitle", "QDesc", "QRemarks", "Rajesh", "Accounts Manager", 0);
        questionnaire.setPostedDate(new Date());
        questionnaire.setDueDate(StringToDate.convertString("20/10/2017"));

        Response response1 = new Response(1, "ResponseDescription", "responseRemarks", "respondentName", "respondentJobTitile", "responseStatus");

        response1.setResponseDate(new Date());
        question1.getResponseList().add(response1);

        questionnaire.getTargetRespondentList().add(target1);
        questionnaire.getQuestionList().add(question1);

        unitTypeService.insertUnitType(unitType);
        unitService.insertUnit(unit);
        responseService.insertResponse(response1);
        targetService.insertTarget(target1);
        questionService.insertQuestion(question1);
        questionnaireService.insertQuestionnaire(questionnaire);
        

        questionService.getAllQuestions();
        questionService.getQuestion(1);
        questionService.getMultipleQuestions(0);
        questionService.updateQuestion("Good afternoon", 1);
        //questionService.deleteQuestion(5);
        //questionnaireDAO.deleteQuestionnaire(1);

        return "home";
    }

    //Navigate to questionnaire generation form
    @RequestMapping(value = "/createQuestionnaire", method = RequestMethod.GET)
    public String createQuestionnaire(Model model) {
        Questionnaire questionnaire=new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);
        return "questionnaire";
    }

    //Insert new questionnaire
    @RequestMapping(value = "/insertQuestionnaire", method = RequestMethod.POST)
    public String insertQuestionnaire(@ModelAttribute("questionnaire")@Valid Questionnaire questionnaire,
                                      BindingResult result, Model model) {
    	target1=new Target(1,"status");
    	Set<Target>targetRespondentList=new HashSet<Target>();
    	targetRespondentList.add(target1);
    	questionnaire.setTargetRespondentList(targetRespondentList);
    	
    	
    	System.out.println(questionnaire);
        if (result.hasErrors()) {
        	System.out.println("Form has errors");
        	
        	List<String> errors = result.getAllErrors().stream()
        	          .map(DefaultMessageSourceResolvable::getDefaultMessage)
        	          .collect(Collectors.toList());
        	System.out.println("Errors: "+errors);
        	        return "questionnaire";
        	
            
        }
        System.out.println("Questionnaire"+questionnaire);
        Question question =new Question();
        model.addAttribute("question", question);
        return "question";
    }

    @ModelAttribute("respondentList")
    public Set<String> getStringRespondentList() {
    	Set<String> targetRespondentList = new HashSet<String>();
        targetRespondentList.add("All Departments");
        targetRespondentList.add("All Regions");
        targetRespondentList.add("All Branches");
        return targetRespondentList;
    }
}
