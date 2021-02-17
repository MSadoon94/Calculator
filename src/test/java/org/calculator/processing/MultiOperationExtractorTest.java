package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MultiOperationExtractorTest {

	@Test
	void shouldReturnArithmeticSectionWithSingleOperation(){
		MultiOperationExtractor extractor = new MultiOperationExtractor();
		String input = TestHelper.MIXED.input().replaceAll("[()]", "");
		assertThat(extractor.extraction(input, Operations.SUBTRACTION), is(equalTo("2-1")));
	}

}
