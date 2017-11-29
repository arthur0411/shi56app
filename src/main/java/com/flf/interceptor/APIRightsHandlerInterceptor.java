package com.flf.interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Service
public class APIRightsHandlerInterceptor extends HandlerInterceptorAdapter {

	private final static Logger log = Logger.getLogger(APIRightsHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getServletPath();
		log.info(request.getHeader("user-agent"));
		Enumeration enu = request.getParameterNames();
		StringBuilder params = new StringBuilder(path);
		params.append("-->");
		String paraName;
		while (enu.hasMoreElements()) {
			paraName = (String) enu.nextElement();
			params.append(paraName).append(": ").append(request.getParameter(paraName)).append("; ");
		}
		log.info(params.toString());
		return true;
	}
}
