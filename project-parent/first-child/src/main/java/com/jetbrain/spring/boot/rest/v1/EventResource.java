package com.jetbrain.spring.boot.rest.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestParam;

@Path("/v1/admin")
public interface EventResource {
	@GET
	@Path("/greeting")
	@Produces({ MediaType.APPLICATION_JSON })
	Response greeting(@RequestParam(value="name", required=false, defaultValue="World") String name);
}
