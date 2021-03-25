package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MultipleValueStrategyTest {

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"PERCENTAGE", "MIXED", "SQUARE_ROOT", "NEGATIVE", "DECIMAL", "NOTATION"})
	void shouldCalculateIncomingBigDecimalValuesBasedOnChosenArithmeticOperation(TestHelper helper){
		MultipleValueStrategy strategy = new MultipleValueStrategy(Operations.valueOf(helper.name()));
		BigDecimal[] values = helper.extracted();

		assertThat(strategy.execute(values), comparesEqualTo(new BigDecimal(helper.answer())));
	}
}
