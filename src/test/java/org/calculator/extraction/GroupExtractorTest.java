package org.calculator.extraction;

import org.calculator.common.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GroupExtractorTest {

	private Request input;
	private GroupExtractor extractor;

	@BeforeEach
	void setUp(){
		extractor = new GroupExtractor();
		input = new Request("(2+2)+(4+2)");
	}

	@Test
	void shouldExtractInnerGroupsFromRequest(){
		Request results = extractor.extraction(input);

		assertThat(results.getInnerGroup(), is("2+2"));
	}
	@Test
	void shouldCreateNewRequestWithParenthesisOfInnerGroupOfInputtedRequestRemoved(){
		Request results = extractor.extraction(input);

		assertThat(results.input(), is("2+2+(4+2)"));
	}
	@Test
	void shouldReturnAmountOfGroupsInRequest(){
		assertThat(extractor.amountOfGroups(input), is(equalTo(2)));
	}
}
