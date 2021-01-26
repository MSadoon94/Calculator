package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.calculator.processing.ProcessorActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.calculator.common.Request;

import java.util.Arrays;

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

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource
	void whenPassedString_ThenWillReturnAnalyzedRequestWithExtractedValuesRelatingToString(TestHelper helper){
		requestAnalyzer.analysis(helper.input());
		Request request = requestBuilder.getBuiltRequest();
		boolean typeIsFunctionOperation = Arrays.stream(Operations.functionOps())
				.anyMatch(op -> op.name().equals(helper.name()));
		if (typeIsFunctionOperation){
			String functionValue = String.valueOf(helper.doubles()[0]);
			assertThat(request.toString(), is(equalTo(functionValue)));
		} else {
			double[] extractedValues = request.getSection(Operations.valueOf(helper.name()));
			assertThat(extractedValues, is(equalTo(helper.doubles())));
		}
	}

}
