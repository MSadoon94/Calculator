package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.ExtractorUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class ProcessorTest {

	private ExtractorUtilities extractor = new ExtractionController().groupExtractor();

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"DECIMAL"})
	void shouldCalculateAnswerForSpecificOperationWhenRequestIsReceived(TestHelper helper){
		Request request = new Request(helper.input());
		Processor processor = new Processor(request, extractor);
		Request result = processor.processedAnswer();

		assertThat(result.input(), comparesEqualTo(helper.answer()));
	}


	@Test
	void shouldProcessInnerGroupCalculationsBeforeOuterGroupCalculations(){
		Request request = new Request("(2+2)+(4-2)");
		Processor processor = new Processor(request, extractor);
		Request result = processor.processedAnswer();

		assertThat(result.input(), comparesEqualTo("6.00"));
	}

	@Test
	void shouldCalculateMultipleOperationInput(){
		Request request = new Request("2+2-2");
		Processor processor = new Processor(request, extractor);
		Request result = processor.processedAnswer();

		assertThat(result.input(), comparesEqualTo("2.00"));
	}
}


