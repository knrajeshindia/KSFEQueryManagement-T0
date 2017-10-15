/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ksfe.model.Query;
import com.ksfe.service.QueryService;
import com.ksfe.service.QueryServiceImpl;

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
	private QueryService queryService;

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
	public String insertQuery(Model model) {
		System.out.println(getClass());
		Query query = new Query();
		query.setBranchID(100);
		query.setQueryDescription("How are you");
		query.setQueryID(100);
		query.setQueryStatus("posted");
		query.setQuestionnaireID(100);
		System.out.println(query);
		model.addAttribute("query", query.getQueryDescription());
		queryService.insertQuery(query);		
		return "home";
	}

}
