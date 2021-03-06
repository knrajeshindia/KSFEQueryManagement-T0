/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.controller;

import com.ksfe.dao.UnitDAOImpl;
import com.ksfe.model.*;
import com.ksfe.service.*;
import com.ksfe.util.StringToDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private UnitDAOImpl unitDAOImpl;
    @Autowired
    private UnitTypeService unitTypeService;
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private TargetService targetService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private AnswerService answerService;

    Response response;
    Question question;
    String jsonResponse;
    List<Integer> questionIDList;
    private Answer answer;
    private ArrayList<Answer> answerList;
    byte attachmentFile[];

	/*
     * @Autowired private QuestionnaireValidator questionnaireValidator;
	 */

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

        // Add custom validators
        // binder.addValidators(questionnaireValidator);

    }


    @RequestMapping(value="/test",method=RequestMethod.GET)
    @ResponseBody
    public String showQuestionnaire() {
        return "";
    }

    @RequestMapping(value="/insertQ",method=RequestMethod.POST)    
    public @ResponseBody String insertQ(@RequestBody Questionnaire questionnaire) {
        System.out.println("Form data binded - Trying to insert " + questionnaire);
        jsonResponse = questionnaireService.insertQuestionnaire(questionnaire);
        System.out.println("Questionnaire inserted"+questionnaire);
        return jsonResponse;
    }

    //Update Questionnaire
    @RequestMapping(value="/updateQ",method=RequestMethod.POST)
    public @ResponseBody void updateQ(@RequestParam("questionIDList") List<Integer> questionIDList,
                                      @RequestParam("questionnaireID") Integer pk ) {
        System.out.println("Update Questionnaire data binded - Trying to UPDATE Questionnaire : " + pk +" | "+questionIDList);
        questionnaireService.updateQuestionnaire(questionIDList,pk);
        System.out.println("Questionnaire UPDATED");
    }


    //Retrieve Questionnaire List
    @RequestMapping(value="/viewQ",method=RequestMethod.POST)
    public @ResponseBody String viewQ(@RequestParam("unitID") Integer unitID) {
        System.out.println(getClass()+" | "+unitID);
        jsonResponse=questionnaireService.viewPendingQuestionnaireList(unitID);
        System.out.println("Questionnaire:"+jsonResponse);
        return jsonResponse;
    }

    //Retrieve Question List
    @RequestMapping(value="/viewQuest",method=RequestMethod.POST)
    public @ResponseBody String viewQuest(@RequestParam("questionIDList") String questionIDListString) {
        questionIDList=new ArrayList<Integer>();
        System.out.println(getClass()+" | "+questionIDListString);
        questionIDList=Arrays.asList(questionIDListString.split(",")).stream()
        		  .map(s -> Integer.parseInt(s))
        		  .collect(Collectors.toList());       
        System.out.println(questionIDList);
        
        
        
        /*for(String a:questionIDListString){
            questionIDList.add(Integer.parseInt(a));
        }*/
        jsonResponse=questionService.viewPendingQuestionList(questionIDList);
        System.out.println("Question List:"+jsonResponse);
        return jsonResponse;
    }


//Insert Question
    @RequestMapping(value="/insertQuest",method=RequestMethod.POST)
    public @ResponseBody String insertQuest(@RequestBody Question question) {
        System.out.println("Form data binded - Trying to insert " + question);
        jsonResponse = questionService.insertQuestion(question);
        System.out.println("Question inserted"+question);
        return jsonResponse;
    }
    
  //Delete Question
    @RequestMapping(value="/deleteQuest",method=RequestMethod.POST)
    public @ResponseBody void deleteQuest(@RequestParam("questionID") Integer pk) {
        System.out.println("Form data binded - Trying to DELETE Question-ID" + pk);
        questionService.deleteQuestion(pk);
        System.out.println("Question DELETED");
        }




    //Insert Answers
    @RequestMapping(value="/saveAnswer",method=RequestMethod.POST)
    public @ResponseBody String saveAnswer(@RequestParam("questionIDList") Integer[] questionIDList,
                                             @RequestParam("answerDescriptionList") String[] answerDescriptionList) {
        System.out.println("Form data binded - Trying to insert " + questionIDList);
        if(questionIDList.length==answerDescriptionList.length) {
            answerList=new ArrayList<Answer>();
            for(int i=0;i<questionIDList.length;i++){
                answer = new Answer();
                answer.setQuestionID(questionIDList[i]);
                answer.setAnswerDescription(answerDescriptionList[i]);
                answerList.add(answer);
            }}
        jsonResponse = answerService.insertAnswer(answerList);
        System.out.println("DATA inserted :"+jsonResponse);
        return jsonResponse;
    }



    //Update Answers
    @RequestMapping(value="/updateAnswer",method=RequestMethod.POST)
    public @ResponseBody String updateAnswer(@RequestParam("answerIDList") Integer[] answerIDList,
                                           @RequestParam("answerDescriptionList") String[] answerDescriptionList) {
        System.out.println("Form data binded - Trying to update Answer ID " + answerIDList);
        if(answerIDList.length==answerDescriptionList.length) {
            answerList=new ArrayList<Answer>();
            for(int i=0;i<answerIDList.length;i++){
                answer = new Answer();
                answer.setAnswerID(answerIDList[i]);
                answer.setAnswerDescription(answerDescriptionList[i]);
                answerList.add(answer);
            }}
        jsonResponse = answerService.updateAnswerList(answerList);
        System.out.println("DATA UPDATED :"+jsonResponse);
        return jsonResponse;
    }


    //Retrieve Answer List
    @RequestMapping(value="/getAnswerList",method=RequestMethod.POST)
    public @ResponseBody String getAnswerList(@RequestParam("responseID") int responseID) {
        System.out.println(getClass()+" | "+responseID);
        jsonResponse=answerService.getAnswerList(responseID);
        System.out.println("Answer List:"+jsonResponse);
        return jsonResponse;
    }


    //Insert Response
    @RequestMapping(value="/saveResponse",method=RequestMethod.POST)
    public @ResponseBody String saveResponse(
            @RequestParam("unitID") Integer unitID,
            @RequestParam("questionnaireID") Integer questionnaireID,
            @RequestParam("responseRemarks") String responseRemarks,
            @RequestParam("attachmentDescription") String attachmentDescription,
           /* @RequestParam("attachmentFile") CommonsMultipartFile file,*/
            @RequestParam("respondentName") String respondentName,
            @RequestParam("respondentJobTitle") String respondentJobTitle,

            @RequestParam("answerIDList") ArrayList<Integer>answerIDList,
            @RequestParam("responseStatus") String responseStatus){
    	System.out.println("Form data binded - Trying to insert " + response);
    	response=new Response();
    	response.setUnitID(unitID);
    	response.setQuestionnaireID(questionnaireID);
    	response.setResponseRemarks(responseRemarks);
    	response.setAttachmentDescription(attachmentDescription);
    	//Attach FILE        
		/*attachmentFile = file.getBytes();
		response.setAttachmentFile(attachmentFile);*/
    	response.setRespondentName(respondentName);
    	response.setRespondentJobTitle(respondentJobTitle);
    	response.setResponseDate(new Date());
    	response.setAnswerIDList(answerIDList);
    	response.setResponseStatus(responseStatus);
        jsonResponse = responseService.insertResponse(response);
        System.out.println("DATA inserted :"+jsonResponse);
        return jsonResponse;
    }


    //UPDATE Response - PUBLISH
    @RequestMapping(value="/updateResponse",method=RequestMethod.POST)
    public @ResponseBody String updateResponse(
            @RequestParam("responseID") Integer responseID,
            @RequestParam("responseRemarks") String responseRemarks,
            @RequestParam("attachmentDescription") String attachmentDescription,
           /* @RequestParam("attachmentFile") CommonsMultipartFile file,*/
            @RequestParam("respondentName") String respondentName,
            @RequestParam("respondentJobTitle") String respondentJobTitle){
        System.out.println("Form data binded - Trying to PUBLISH RESPONSE: " + responseID);
        response=new Response();
        response.setResponseID(responseID);
        response.setResponseRemarks(responseRemarks);
        response.setAttachmentDescription(attachmentDescription);
        //Attach FILE
		/*attachmentFile = file.getBytes();
		response.setAttachmentFile(attachmentFile);*/
        response.setRespondentName(respondentName);
        response.setRespondentJobTitle(respondentJobTitle);
        jsonResponse = responseService.updateResponse(response);
        System.out.println("DATA inserted :"+jsonResponse);
        return jsonResponse;
    }


    //Retrieve Response
    @RequestMapping(value="/getResponse",method=RequestMethod.POST)
    public @ResponseBody String getResponse(@RequestParam("responseID") Integer responseID) {
        System.out.println(getClass()+" | "+responseID);
        jsonResponse=responseService.getResponse(responseID);
        System.out.println("Response: "+jsonResponse);
        return jsonResponse;
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

    
    
    @RequestMapping(value = "/testResponse", method = RequestMethod.GET)
    public String testResponse() {

        //RESPONSE
        response = new Response();
        response.setResponseID(20); 
        response.setResponseRemarks("REMARKS");
        response.setAttachmentDescription("DESCRIPTION");
        response.setRespondentName("Name");
        response.setRespondentJobTitle("respondentJobTitle");
        responseService.getResponse(response.getResponseID()); 
        responseService.updateResponse(response);
        return "home";
    }
    
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String insertQuestion(Model model) {
        System.out.println(getClass());

        //UNIT TYPE
        UnitType unitType = new UnitType("UnitType", "Eligibility");
        unitTypeService.insertUnitType(unitType);
        System.out.println("Unit Type inserted" + unitType);

        //UNIT
        Unit unit = new Unit(1, "Password", "UnitName", "Code", "Address", "District", "Manager", "Email", "Mobile",
                "Telephone", "Status");
        unit.setUnitTypeID(1);
        unitService.insertUnit(unit);
        System.out.println("Unit inserted" + unit);

        //QUESTIONNAIRE
        Questionnaire questionnaire = new Questionnaire("QTitle", "QDesc", "QRemarks", "Rajesh", "Accounts Manager");
        questionnaire.setPostedDate(new Date());
        questionnaire.setDueDate(StringToDate.convertString("20/10/2017"));
        questionnaire.getTargetRespondentIDList().add(1);
        questionnaireService.insertQuestionnaire(questionnaire);

        //QUESTION
        question = new Question("Hi How are you", "Data Type");
        question.setQuestionnaireID(1);
        questionService.insertQuestion(question);

        questionService.getAllQuestions();
        questionService.getQuestion(1);
        questionService.getMultipleQuestions(0);
        questionService.updateQuestion("Good afternoon", 1);

        // questionService.deleteQuestion(5);
        // questionnaireDAO.deleteQuestionnaire(1);

        //RESPONSE
        response = new Response(1, 1, "respondentName", "respondentJobTitle");
        response.setResponseDate(new Date());
        response.setResponseRemarks("Remarks");
        responseService.insertResponse(response);

        return "home";
    }

    // Navigate to questionnaire generation form
    @RequestMapping(value = "/createQuestionnaire", method = RequestMethod.GET)
    public String createQuestionnaire(Model model) {
        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        //Manual setting of question object-Change later
        question = new Question();
        model.addAttribute("question", question);
        question.setQuestionnaireID(1);

        //Manual setting of Response object-Change later
        response = new Response(1, 1, "respondentName", "respondentJobTitle");
        response.setResponseDate(new Date());
        response.setResponseRemarks("Remarks");
        model.addAttribute("response", response);

        return "home-admin";
    }

    // Insert new questionnaire
    /*@RequestMapping(value = "/insertQuestionnaire", method = RequestMethod.POST)
    public String insertQuestionnaire(@ModelAttribute("questionnaire") @Valid Questionnaire questionnaire,
                                      BindingResult result, Model model) {
        System.out.println(questionnaire);
        if (result.hasErrors()) {
            System.out.println("Form has errors" + result.getAllErrors());
            return "questionnaire";

        }
        System.out.println("Questionnaire" + questionnaire);
        //Manual setting of question object-Change later
        question = new Question();
        model.addAttribute("question", question);
        question.setQuestionnaireID(1);
        return "question";

    }*/

    // Insert new question
    @RequestMapping(value = "/insertQuestion", method = RequestMethod.POST)
    public String insertQuestion(@Valid @ModelAttribute("question") Question question, BindingResult result,
                                 Model model) {
        question.setQuestionnaireID(1);
        if (result.hasErrors()) {
            System.out.println("Form has errors" + result.getAllErrors());
            System.out.println("Received question object" + question);
            return "question";
        }
        System.out.println("Trying to insert Question" + question);
        questionService.insertQuestion(question);
        System.out.println("Question inserted");

        //Manual setting of Response object-Change later
        response = new Response(1, 1, "respondentName", "respondentJobTitle");
        response.setResponseDate(new Date());
        response.setResponseRemarks("Remarks");
        model.addAttribute("response", response);
        return "response";
    }

    // Insert new Response
    @RequestMapping(value = "/insertResponse", method = RequestMethod.POST)
    public String insertResponse(@Valid @ModelAttribute("response") Response response, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            System.out.println("Form has errors" + result.getAllErrors());
            System.out.println("Received response object" + response);
            return "response";
        }
        System.out.println("Trying to insert Response" + response);
        response.setResponseDate(new Date());
        response.setResponseRemarks("Remarks");
        responseService.insertResponse(response);
        System.out.println("Response inserted");
        return "demo";
    }
    
    
    
    
    

 // DUMMY
    @RequestMapping(value = "/dumy", method = RequestMethod.GET)
    public String dummy() {
        System.out.println("Trying to call UNITDAOIMPL");
        unitDAOImpl.getClass();
        System.out.println("ID List");
        return "";
    }
    
 // RESPONSE
    @RequestMapping(value = "/response", method = RequestMethod.GET)
    public String response() {
        System.out.println("Trying to call Response");
        return "home-branch";
    }

    
    
    
    
    
    
    //List out target Respondants for Questionnaire
    @ModelAttribute("respondentList")
    public Set<Integer> getTargetRespondentList() {
        Set<Integer> targetRespondentList = new HashSet<Integer>();
        targetRespondentList.add(1);
        targetRespondentList.add(2);
        targetRespondentList.add(3);
        return targetRespondentList;
    }

    //List out data types expected for response
    @ModelAttribute("dataTypeList")
    public Set<String> getDataTypeList() {
        Set<String> dataTypeList = new HashSet<String>();
        dataTypeList.add("Text");
        dataTypeList.add("Number");
        dataTypeList.add("Date");
        return dataTypeList;
    }
}
