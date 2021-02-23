package org.calculator.request;

import org.calculator.common.Request;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GroupExtractorTest {

	private String input = "(2+2)+(4+2)";

	@Test
	void shouldSplitRequestIntoGroupedCalculationAndRemainingCalculation(){
		GroupExtractor extractor = new GroupExtractor();
		Request request = new Request(input);

		String[] groups = extractor.extraction(request);

		assertThat(groups, arrayContaining("2+2", "2+2+(4+2)"));
	}
	@Test
	void shouldReturnAmountOfGroupsInRequest(){
		GroupExtractor extractor = new GroupExtractor();
		Request request = new Request(input);

		assertThat(extractor.amountOfGroups(request), is(equalTo(2)));
	}
}
