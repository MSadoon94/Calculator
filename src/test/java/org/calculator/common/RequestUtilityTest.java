package org.calculator.common;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestUtilityTest {

	@Test
	void shouldConvertInputValuesIntoBigDecimals(){
		Request request = new Request(TestHelper.ADDITION.input());
		request.setOperation(Operations.ADDITION);
		RequestUtility utility = new RequestUtility(request);

		assertThat(utility.bigDecimalValues(),
				is(arrayContaining(new BigDecimal("2"), new BigDecimal("2"))));
	}
}
