package com.gateway.demo.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
/**
 * @author aashish
 */
@Component
public class ApiRoute extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		from("direct:searchCriteria")
			.log("sending request to prepare query with criteria : ${header.criteria}")
			.setHeader(Exchange.HTTP_METHOD, constant("get"))
			.setHeader(Exchange.HTTP_PATH, constant("")) 
			/*.setHeader(Exchange.HTTP_URI, simple("")) 
			.setHeader(Exchange.TO_ENDPOINT, simple("")) */
			.setHeader(Exchange.HTTP_QUERY,simple("criteria=${header.criteria}"))
			//.setHeader(Exchange.HTTP_URL, simple("")) 
			.choice()
				.when(simple("${header.criteria}"))
				/*.process(e -> { e.getIn().setBody(simple("${header.criteria}"));	
				}).log("printing : ${body}")*/
					.toD("jetty://http://localhost:9002/generate?bridgeEndpoint=true")
					.log("prepared query : ${body} ")
					.setHeader(Exchange.HTTP_METHOD, simple("POST"))
					.toD("jetty://http://localhost:9001/process?bridgeEndpoint=true")
				.otherwise()
					.log("Please give the criteria")
					.setBody().simple("criteria parameter should not be empty");
	}
}
