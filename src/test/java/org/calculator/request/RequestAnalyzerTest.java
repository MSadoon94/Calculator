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

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class RequestAnalyzerTest {
	@Mock private ProcessorActions processor;
	private RequestAnalyzer requestAnalyzer;
	private RequestBuilder requestBuilder;
	private Request request;

	@BeforeEach
	void setUp(){
		requestBuilder = new RequestBuilder();
		requestAnalyzer = new RequestAnalyzer(processor, requestBuilder);
	}

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource
	void shouldExtractValuesFromStringIntoEachSectionOfRequest(TestHelper helper){
		requestAnalyzer.analysis(helper.input());
		request = requestBuilder.getBuiltRequest();
		if (helper == TestHelper.SINGLE_VALUE){
			singleValueAssert(helper);
		} else {
			operationAssert(helper);
		}
	}

	private void singleValueAssert(TestHelper helper){
		String functionValue = String.valueOf(helper.extracted()[0]);
		assertThat(request.toString(), is(equalTo(functionValue)));
	}

	private void operationAssert(TestHelper helper){
		BigDecimal[] extractedValues = request.getSection(Operations.valueOf(helper.name()));
		for (int i = 0; i < extractedValues.length; i++){
			assertThat(extractedValues[i], comparesEqualTo(helper.extracted()[i]));
		}
	}

}
