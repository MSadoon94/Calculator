package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.calculator.processing.ProcessorActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.calculator.common.Request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class RequestAnalyzerTest {
	@Mock private ProcessorActions processor;
	private RequestAnalyzer requestAnalyzer;
	private RequestBuilder requestBuilder;

	@BeforeEach
	void setUp(){
		requestBuilder = new RequestBuilder();
		requestAnalyzer = new RequestAnalyzer(processor, requestBuilder);
	}

	@Test
	void whenStringIsSingleValue_ThenWillReturnAnalyzedRequestWithExtractedSingleValue(){
		requestAnalyzer.analysis(TestHelper.SINGLE_VALUE.input());
		Request request = requestBuilder.getBuiltRequest();
		assertThat(request.toString(), is(equalTo(TestHelper.SINGLE_VALUE.answer())));
	}

	@Test
	void whenStringHasAddition_ThenWillReturnAnalyzedRequestWithExtractedAdditionValues(){
		requestAnalyzer.analysis(TestHelper.ADDITION.input());
		Request request = requestBuilder.getBuiltRequest();
		assertThat(request.getSection(Operations.ADDITION), is(equalTo(TestHelper.ADDITION.doubles())));
	}

	@Test
	void whenStringHasSubtraction_ThenWillReturnAnalyzedRequestWithExtractedSubtractionValues(){

		requestAnalyzer.analysis(TestHelper.SUBTRACTION.input());
		Request request = requestBuilder.getBuiltRequest();
		assertThat(request.getSection(Operations.SUBTRACTION), is(equalTo(TestHelper.SUBTRACTION.doubles())));
	}

	@Test
	void whenStringHasMultiplication_ThenWillReturnAnalyzedRequestWithExtractedMultiplicationValues(){
		requestAnalyzer.analysis(TestHelper.MULTIPLICATION.input());
		Request request = requestBuilder.getBuiltRequest();
		assertThat(request.getSection(Operations.MULTIPLICATION), is(equalTo(TestHelper.MULTIPLICATION.doubles())));
	}
}
