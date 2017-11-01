package com.ksfe.dao;

import com.ksfe.model.Response;

import java.util.List;

public interface ResponseDAO {

	public Response insertResponse(Response response) ;


    Response verifyResponse(Integer questionnaireID);

    Response getResponse(int responseID);

    Response updateResponse(Response response);
}
