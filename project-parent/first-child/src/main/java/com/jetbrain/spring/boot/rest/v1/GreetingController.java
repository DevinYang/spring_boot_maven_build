package com.jetbrain.spring.boot.rest.v1;


import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;



@Service
public class GreetingController implements EventResource{

	private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    


	public Response greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
		return Response.status(HttpStatus.SC_CREATED).entity(String.format(template, name)).build();
	}
    
}
