package org.calculator.answer;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AnswerBuilderTest {
	private AnswerBuilder builder;

	@BeforeEach
	void setUp(){
		builder = new AnswerBuilder();
	}
	@Test
	void shouldBuildAnswerFromGivenDouble(){
		String empty = "";
		BigDecimal value =
				TestHelper.SINGLE_VALUE.extracted()[0].setScale(2, RoundingMode.HALF_UP);
		assertThat(builder.answer(empty, value).toString(), is(TestHelper.SINGLE_VALUE.answer()));
	}
	@Test
	void shouldAddSymbolsToAnswersThatNeedThem(){
		BigDecimal value = TestHelper.PERCENTAGE.extracted()[0];
		String percent = "%";
		String answer = builder.answer(percent, value).toString();
		assertThat(answer.contains("%"), is(true));
	}

}
