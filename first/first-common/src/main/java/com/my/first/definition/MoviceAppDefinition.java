/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.definition;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface MoviceAppDefinition {

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.POST)
    public String hi(@RequestHeader("id") String customerId);
}