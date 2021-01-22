package org.calculator.requesttests;

import org.calculator.common.Operations;
import org.calculator.processing.ProcessorActions;
import org.calculator.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.calculator.common.Request;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class RequestAnalyzerTest {
	@Mock private ProcessorActions processor;
	private RequestBoundary requestController = new RequestController();
	private Analyzer requestAnalyzer;
	private Builder requestBuilder;
	private double[] values = {2.0,2.0,3.0,4.0};

	@BeforeEach
	void setUp(){

		requestBuilder = requestController.requestBuilder();
		requestAnalyzer = requestController.requestAnalyzer(processor, requestBuilder);
	}
	@Test
	void whenStringIsSingleValue_ThenWillReturnAnalyzedRequestWithExtractedSingleValue(){
		String singleValue = ("2.0");
		requestAnalyzer.analysis(singleValue);
		Request request = requestBuilder.getBuiltRequest();
		assertThat(request.toString(), is(equalTo(singleValue)));
	}
	@Test
	void whenStringHasAddition_ThenWillReturnAnalyzedRequestWithExtractedAdditionValues(){
		String addValue = ("2+2+3+4");
		requestAnalyzer.analysis(addValue);
		Request request = requestBuilder.getBuiltRequest();
		assertThat(Arrays.toString(request.getSection(Operations.ADDITION)), is(equalTo(Arrays.toString(values))));
	}
	@Test
	void whenStringHasSubtraction_ThenWillReturnAnalyzedRequestWithExtractedSubtractionValues(){
		String minusValue = ("2-2-3-4");
		requestAnalyzer.analysis(minusValue);
		Request request = requestBuilder.getBuiltRequest();
		assertThat(Arrays.toString(request.getSection(Operations.SUBTRACTION)), is(equalTo(Arrays.toString(values))));
	}

	@Test
	void whenStringHasMultiplication_ThenWillReturnAnalyzedRequestWithExtractedMultiplicationValues(){
		String minusValue = ("2*2*3*4");
		requestAnalyzer.analysis(minusValue);
		Request request = requestBuilder.getBuiltRequest();
		assertThat(Arrays.toString(request.getSection(Operations.MULTIPLICATION)), is(equalTo(Arrays.toString(values))));
	}
}
