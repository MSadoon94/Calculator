package org.calculator.request;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ValueExtractorTest {

	private Operations targetOperation = Operations.ADDITION;

	@Test
	void shouldExtractValuesBasedOnTargetOperation(){
		ValueExtractor extractor = new ValueExtractor();
		Request request = new Request("2+2");

		request.setOperation(targetOperation);
		String[] values = extractor.extraction(request);

		assertThat(values, is(arrayContaining("2", "2")));
	}

}
