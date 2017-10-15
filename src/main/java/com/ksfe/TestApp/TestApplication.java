package com.ksfe.TestApp;

import com.ksfe.model.Query;
import com.ksfe.service.QueryService;
import com.ksfe.service.QueryServiceImpl;

public class TestApplication {
    public static void main(String[] args) {
        Query query=new Query();
        query.setBranchID(100);
        query.setQueryDescription("How are you");
        query.setQueryID(100);
        query.setQueryStatus("posted");
        query.setQuestionnaireID(100);
        QueryService queryService=new QueryServiceImpl();
        queryService.insertQuery(query);
    }
}
