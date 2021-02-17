package org.calculator.processing;

import org.calculator.common.TestHelper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import org.mockito.junit.jupiter.MockitoExtension;

import org.calculator.common.Request;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ProcessorTest {

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource
	void shouldCalculateAnswerForSpecificOperationWhenRequestIsReceived(TestHelper helper){
		Request request = new Request(helper.input());
		Processor processor = new Processor(request);

		assertThat(processor.processedAnswer(), comparesEqualTo(new BigDecimal(helper.answer())));
	}


	@Test
	void shouldProcessInnerGroupCalculationsBeforeOuterGroupCalculations(){
		Request request = new Request("(2+2)+(4-2)");
		Processor processor = new Processor(request);
		assertThat(processor.processedAnswer(), comparesEqualTo(new BigDecimal("6")));
	}

	@Test
	void shouldCalculateMultipleOperationInput(){
		Request request = new Request("2+2-2");
		Processor processor = new Processor(request);

		assertThat(processor.processedAnswer(), comparesEqualTo(new BigDecimal("2.00")));
	}
}


