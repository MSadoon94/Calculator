package org.calculator.processing;
import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SubProcessorTest {


	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"SINGLE_VALUE", "PERCENTAGE", "MIXED", "SQUARE_ROOT", "NEGATIVE"})
	void shouldCalculateSingleArithmeticOperationAnswers(TestHelper helper){
		SubProcessor processor = new SubProcessor(helper.input());
		assertThat(new BigDecimal(processor.subSection(Operations.valueOf(helper.name()))),
				comparesEqualTo(new BigDecimal(helper.answer())));
	}



}
