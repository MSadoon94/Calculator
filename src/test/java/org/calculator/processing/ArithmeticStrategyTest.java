package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ArithmeticStrategyTest {

	@Test
	void givenPassedRequest_WhenRequestIsArithmeticOperation_ThenWillSumAllArithmeticSectionsOfRequestIntoBigDecimal(){
		ArithmeticStrategy strategy = new ArithmeticStrategy();
		Request request = new Request("2.0");
		BigDecimal answer = BigDecimal.ZERO;
		BigDecimal[] value = {BigDecimal.valueOf(2.0)};
		for (Operations op : Operations.arithmeticOps()){
			request.setSection(op, value);
			answer = answer.add(value[0]);
		}
		assertThat(strategy.execute(request), is(answer));
	}

}
