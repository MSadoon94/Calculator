package org.calculator.extraction;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MultiOperationExtractorTest {

	@Test
	void shouldReturnArithmeticSectionWithSingleOperation(){
		MultiOperationExtractor extractor = new MultiOperationExtractor();

		String inputString = TestHelper.MIXED.input().replaceAll("[()]", "");
		Request input = new Request(inputString);
		input.setOperation(Operations.SUBTRACTION);
		Request results = extractor.extraction(input);

		assertThat(results.input(), is(equalTo("2-1")));
	}

}
