package org.calculator.request;

import org.junit.jupiter.api.Test;
import org.calculator.common.Request;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestBuilderTest {

	@Test
	void whenPassedString_ThenShouldBuildAnalyzedRequestThatContainsSameString(){
		Builder builder = new RequestController().requestBuilder();
		String input = "2.0";
		builder.addOriginalInput(input);
		Request builtRequest = builder.getBuiltRequest();
		assertThat(builtRequest.getClass(), is(Request.class));
		assertThat(builtRequest.toString(), is(equalTo(input)));
	}
}
