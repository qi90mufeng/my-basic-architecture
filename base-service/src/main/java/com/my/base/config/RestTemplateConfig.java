/**
 * 
 */
package com.my.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:restTemplate-application.xml"})
public class RestTemplateConfig {

}
