package org.calculator.extraction;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

public class ValueExtractorTest {

	@Test
	void shouldExtractValuesBasedOnTargetOperation(){
		ValueExtractor extractor = new ValueExtractor();
		Request request = new Request(TestHelper.ADDITION.input());

		request.setOperation(Operations.ADDITION);
		Request results = extractor.extraction(request);

		assertThat(results.getValues(), is(arrayContaining("2", "2")));
	}
	@Test
	void shouldRemoveVoidValuesFromCreatedStringArray(){
		ValueExtractor extractor = new ValueExtractor();
		Request request = new Request(TestHelper.SQUARE_ROOT.input());

		request.setOperation(Operations.SQUARE_ROOT);
		Request results = extractor.extraction(request);

		assertThat(results.getValues(), is(arrayContaining("4")));
	}

}
