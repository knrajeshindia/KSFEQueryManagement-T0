/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.ksfe.model.Unit;
import com.ksfe.model.UnitType;
import com.ksfe.service.UnitService;
import com.ksfe.service.UnitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ksfe.model.Question;
import com.ksfe.service.QuestionService;

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
        UnitType unitType = new UnitType(1, "UnitType", "Eligibility");
        Unit unit = new Unit(100, 100, 1, "UName", "UCode", "UAddress", "UDistrict", "UManager", "Email", "Mobile", "Telephone", "Status");
        Question question = new Question(100, "Hi How are you", "Remarks", 1000, "draft");
        System.out.println(question);
        model.addAttribute("question", question.getQuestionDescription());
        unitService.insertUnit(unit);
        unitTypeService.insertUnitType(unitType);
        questionService.insertQuestion(question);

        return "home";
    }

}
