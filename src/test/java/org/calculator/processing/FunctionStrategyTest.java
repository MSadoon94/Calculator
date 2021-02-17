package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FunctionStrategyTest {

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource(names = {"SINGLE_VALUE","PERCENTAGE"})
	void shouldDoFunctionBasedOnOperationType(TestHelper helper){

		FunctionStrategy strategy = new FunctionStrategy(Operations.valueOf(helper.name()));

		assertThat(
				strategy.execute(helper.extracted()),
				comparesEqualTo(new BigDecimal(helper.answer()))
		);
	}




}
