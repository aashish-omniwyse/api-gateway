package com.gateway.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class ApiRestConfiguration extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		restConfiguration().component("jetty")
		.host("localhost")
		.port("9005")
		.bindingMode(RestBindingMode.auto);
		
		rest("/search?criteria").get().to("direct:searchCriteria");
	}
}
