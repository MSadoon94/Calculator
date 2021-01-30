package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FunctionStrategyTest {

	@Test
	void shouldSumAllFunctionSectionsOfRequestIntoSingleBigDecimal(){
		FunctionStrategy strategy = new FunctionStrategy();
		String input = TestHelper.SINGLE_VALUE.input();
		Request request = new Request(input);
		BigDecimal answer = BigDecimal.valueOf((Operations.functionOps().length * 100) + Double.parseDouble(input));
		BigDecimal[] value = {BigDecimal.valueOf(2.0)};
		for (Operations op : Operations.functionOps()){
			request.setSection(op, value);
		}
		assertThat(strategy.execute(request), is(answer));
	}

}
