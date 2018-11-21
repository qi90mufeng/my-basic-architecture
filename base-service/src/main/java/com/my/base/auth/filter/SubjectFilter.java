package com.my.base.auth.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.my.base.auth.Subject;
import com.my.base.auth.SubjectUtils;

/**
 * 权限过滤器
 *
 */
public class SubjectFilter extends OncePerRequestFilter {
	private String env = "dev";
	public SubjectFilter(String profiles) {
		this.env = profiles;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String id = request.getHeader("id");
		String name = request.getHeader("name");
		if (StringUtils.isNotEmpty(id)) {
			Subject subject = new Subject();
			subject.setId(id);
			if (StringUtils.isNotEmpty(name)) {
				subject.setName(URLDecoder.decode(name, "UTF-8"));
			}
			SubjectUtils.set(subject);
		} else {
//			if (env.equals("dev")) {
//				Subject subject = new Subject();
//				subject.setId("1");
//				subject.setName("开发");
//				SubjectUtils.set(subject);
//			}
		}
		filterChain.doFilter(request, response);
	}

}
