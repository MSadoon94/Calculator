package org.calculator.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestHandlerTest {

	private String request = "2";
	private RequestServices handler;

	@BeforeEach
	void setUp(){
		handler = new RequestController().requestHandler();
	}

	@Test
	void shouldStoreRequestInRequestCache(){
		handler.addRequest(request);
		assertThat(handler.getRequest().toString(), is(request));
	}

}
