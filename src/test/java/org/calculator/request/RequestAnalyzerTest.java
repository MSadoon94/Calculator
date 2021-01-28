package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.calculator.processing.ProcessorActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.calculator.common.Request;

import java.math.BigDecimal;
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
	void whenPassedString_ThenWillReturnRequestWithExtractedValuesRelatingToString(TestHelper helper){
		requestAnalyzer.analysis(helper.input());
		System.out.println(Arrays.toString(helper.extracted()));
		Request request = requestBuilder.getBuiltRequest();
		if (helper == TestHelper.SINGLE_VALUE){
			String functionValue = String.valueOf(helper.extracted()[0]);
			assertThat(request.toString(), is(equalTo(functionValue)));
			System.out.println(request.toString() + " " + functionValue);
		} else {
			BigDecimal[] extractedValues = request.getSection(Operations.valueOf(helper.name()));
			assertThat(extractedValues, is(equalTo(helper.extracted())));
			System.out.println( Arrays.toString(extractedValues) + " " + Arrays.toString(helper.extracted()));
		}
	}


}
