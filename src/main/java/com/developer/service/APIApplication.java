package com.developer.service;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;


public class APIApplication extends ResourceConfig {
	
	public APIApplication() {
        //����Resource
        register(EmployeeController.class);

        //ע������ת����
        register(JacksonJsonProvider.class);

        // Logging.
        register(LoggingFilter.class);
    }

}