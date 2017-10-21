package com.ksfe.util;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author MyPc
 */
public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String convertJavaToJson(Object obj) {
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            System.out.println("Exception Occured while converting the java obj into json : " + e);
        } catch (JsonMappingException e) {
            System.out.println("Exception Occured while converting the java obj into json : " + e);
        } catch (IOException e) {
            System.out.println("Exception Occured while converting the java obj into json : " + e);
        }
        return jsonStr;
    }

    public static <T> T convertJsonToJava(String str, Class<T> cls) {
        T obj = null;
        try {
            obj = objectMapper.readValue(str, cls);
        } catch (JsonParseException e) {
            System.out.println("Exception Occured while converting the json  into java : " + e);
        } catch (JsonMappingException e) {
            System.out.println("Exception Occured while converting the json  into java : " + e);
        } catch (IOException e) {
            System.out.println("Exception Occured while converting the json  into java : " + e);
        }
        return obj;
    }
}

