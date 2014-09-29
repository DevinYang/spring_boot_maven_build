package com.jetbrain.spring.boot;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.RequestContextFilter;

import com.jetbrain.spring.boot.rest.v1.EventResource;



public class JerseyConfig extends ResourceConfig {

	@Inject
	public JerseyConfig(ServletContext servletContext) {
		WebApplicationContext springFactory = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		register(RequestContextFilter.class);
		
		register(springFactory.getBean(EventResource.class));
		 
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
        property(ServerProperties.JSON_PROCESSING_FEATURE_DISABLE, false);
       
        
        // property(ServerProperties.WADL_FEATURE_DISABLE, true);
        register(JacksonFeature.class);
        setApplicationName("first-child");		
	}
}
