package org.calculator.common;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestUtilityTest {
	private RequestUtility utility = new RequestUtility();

	@Test
	void shouldConvertInputValuesIntoBigDecimals(){
		Request request = new Request(TestHelper.ADDITION.input());
		request.setOperation(Operations.ADDITION);


		assertThat(utility.bigDecimalValues(request),
				is(arrayContaining(new BigDecimal("2"), new BigDecimal("2"))));
	}

	@Test
	void shouldRemoveVoidValues(){
		Request request = new Request(TestHelper.SQUARE_ROOT.input());
		request.setOperation(Operations.SQUARE_ROOT);

		assertThat(utility.bigDecimalValues(request),
				is(arrayContaining(new BigDecimal("4"))));
	}

	@Test
	void shouldReturnAmountOfOperationsInRequest(){
		Request request = new Request(TestHelper.MIXED.input());

		assertThat(utility.amountOfOperators(request), is(4L));
	}

	@Test
	void shouldReturnListOfOperatorsInRequest(){
		Request request = new Request(TestHelper.SUBTRACTION.input());

		assertThat(utility.operatorList(request), is(allOf(iterableWithSize(1), hasItem(Operations.SUBTRACTION))));
	}
}
