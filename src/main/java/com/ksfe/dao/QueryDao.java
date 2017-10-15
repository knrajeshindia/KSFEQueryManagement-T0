/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Query;
import org.springframework.stereotype.Repository;
/**
 * This is a Java interface for defining DAO operations
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface QueryDao {
    void insertQuery(Query query);
}
