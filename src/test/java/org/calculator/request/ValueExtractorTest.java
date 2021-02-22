package org.calculator.request;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ValueExtractorTest {

	@Test
	void shouldExtractValuesBasedOnTargetOperation(){
		ValueExtractor extractor = new ValueExtractor();
		Request request = new Request(TestHelper.ADDITION.input());

		request.setOperation(Operations.ADDITION);

		assertThat(extractor.extraction(request), is(arrayContaining("2", "2")));
	}
	@Test
	void shouldRemoveVoidValuesFromCreatedStringArray(){
		ValueExtractor extractor = new ValueExtractor();
		Request request = new Request(TestHelper.SQUARE_ROOT.input());

		request.setOperation(Operations.SQUARE_ROOT);

		assertThat(extractor.extraction(request), is(arrayContaining("4")));
	}

}
