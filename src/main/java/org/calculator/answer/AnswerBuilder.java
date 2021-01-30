package org.calculator.answer;

import org.calculator.common.Answer;
import java.math.BigDecimal;

public class AnswerBuilder {

	public Answer answer(String symbol, BigDecimal value) {
		return new Answer(value + symbol);
	}

}
