package com.ksfe.dao;

import com.ksfe.model.Response;

public interface ResponseDAO {

	public Response insertResponse(Response response) ;


    String verifyResponse(Integer questionnaireID);
}
