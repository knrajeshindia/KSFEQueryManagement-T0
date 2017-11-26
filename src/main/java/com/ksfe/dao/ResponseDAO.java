package com.ksfe.dao;

import com.ksfe.model.Response;

public interface ResponseDAO {

	public Response insertResponse(Response response) ;


    Response verifyResponse(Integer questionnaireID, Integer unitID);

    Response getResponse(int responseID);

    Response updateResponse(Response response);

    int getResponsePercentage(Integer questionnaireID);
}
