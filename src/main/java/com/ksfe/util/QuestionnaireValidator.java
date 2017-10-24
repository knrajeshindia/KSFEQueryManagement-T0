package com.ksfe.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ksfe.model.Questionnaire;

@Component
public class QuestionnaireValidator implements Validator {

   @Override
   public boolean supports(Class<?> clazz) {
      return Questionnaire.class.equals(clazz);
   }

   @Override
   public void validate(Object obj, Errors err) {
      ValidationUtils.rejectIfEmpty(err, "questionnaireTitle", "questionnaire.questionnaireTitle.empty");
      //Questionnaire questionnaire=(Questionnaire)obj;
      
   }

}