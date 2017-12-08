/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.definition;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface  MovieResourceDefinition {

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
    public String hi(@PathVariable("id") String customerId);
}