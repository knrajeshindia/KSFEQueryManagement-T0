/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.model.Response;

;

/**
 * This is a Java Interface declaring Service methods
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface ResponseService {
    public String insertResponse(Response response);
    public String verifyResponse(Integer questionnaireID);
}
