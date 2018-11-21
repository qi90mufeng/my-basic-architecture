package com.my.base.auth;

import com.my.base.auth.filter.SubjectFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubjectConfiguration {
	@Value("${spring.profiles.active}")
	private String profiles;
	@Bean
	public FilterRegistrationBean subjectFilter(){
		SubjectFilter filter=new SubjectFilter(profiles);
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
