package com.ksfe.dao;

import com.ksfe.model.Query;
import org.springframework.stereotype.Repository;

public interface QueryDao {
    void insertQuery(Query query);
}
