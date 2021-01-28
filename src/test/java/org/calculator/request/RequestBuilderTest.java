package org.calculator.request;

import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;
import org.calculator.common.Request;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestBuilderTest {

	@Test
	void whenPassedString_ThenShouldBuildRequestThatContainsSameString(){
		RequestBuilder builder = new RequestBuilder();
		builder.addOriginalInput(TestHelper.SINGLE_VALUE.input());
		Request builtRequest = builder.getBuiltRequest();
		assertThat(builtRequest.getClass(), is(Request.class));
		assertThat(builtRequest.toString(), is(equalTo(TestHelper.SINGLE_VALUE.input())));
	}
}
