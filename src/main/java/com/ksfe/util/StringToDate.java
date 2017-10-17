/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ksfe.model.Question;

/**
 * This is a Java utility class to convert Strng to Util Date.
 *
 * @author RNarendran
 * @since 1.0,
 */
public class StringToDate {
	static Date formatedDate=null;
    public static Date convertString(String dateInString) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    	
    	System.out.println("Date as String : "+dateInString);
    	try {
    		 formatedDate= formatter.parse(dateInString);
    		 System.out.println("Date as Date object:"+formatedDate);
    		  } catch (ParseException e) {
    		    e.printStackTrace();
    		}
    		return formatedDate;
    		}
}





