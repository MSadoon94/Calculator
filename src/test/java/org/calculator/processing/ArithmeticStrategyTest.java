package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ArithmeticStrategyTest {

	@Test
	void givenPassedRequest_WhenRequestIsArithmeticOperation_ThenWillSumAllSectionsOfRequestIntoDouble(){
		ArithmeticStrategy strategy = new ArithmeticStrategy();
		Request request = new Request(TestHelper.SINGLE_VALUE.input());
		double answer = 0;

		for (Operations op : arithmeticOperations()){
			request.setSection(op, new double[]{2});
			answer += 2;
		}
		assertThat(strategy.execute(request), is(answer));
	}

	private Operations[] arithmeticOperations(){
		return Arrays.stream(Operations.values())
				.filter(op -> op != Operations.SINGLE_VALUE)
				.filter(op -> op != Operations.PERCENTAGE)
				.toArray(Operations[]::new);
	}
}
