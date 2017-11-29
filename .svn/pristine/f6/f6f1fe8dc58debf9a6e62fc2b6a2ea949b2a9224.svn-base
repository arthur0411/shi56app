package com.flf.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.base.criteria.Criteria;
import com.flf.entity.SystemConfiguration;
import com.flf.service.RedisSpringProxy;
import com.flf.service.SystemConfigurationService;
import com.flf.util.Const;

public class WebAppContextListener implements ServletContextListener {
	
	private final static Logger log = Logger.getLogger(WebAppContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		log.info("获取Spring WebApplicationContext");
		
	}

}
