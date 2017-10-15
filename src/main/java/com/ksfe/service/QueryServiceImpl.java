package com.ksfe.service;

import com.ksfe.dao.QueryDao;
import com.ksfe.dao.QueryDaoImpl;
import com.ksfe.model.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
	private QueryDao queryDao;

    @Transactional
    public void insertQuery(Query query) {
        System.out.println(getClass());
        queryDao.insertQuery(query);
    }
}
