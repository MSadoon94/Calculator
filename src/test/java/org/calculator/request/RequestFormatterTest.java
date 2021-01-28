package org.calculator.request;

import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestFormatterTest {

	@Test
	void whenPassedStringArray_ThenWillParseStringArrayToBigDecimalArrayWithSameValues(){
		RequestFormatter requestFormatter = new RequestFormatter();
		String[] input = {"0", "2", "4"};
		BigDecimal[] decimals = new BigDecimal[input.length];
		for (int i = 0; i < input.length; i++) {
			decimals[i] = new BigDecimal(input[i]);
		}
		BigDecimal[] returned = requestFormatter.format(input);

		assertThat(returned.getClass(), is(equalTo(BigDecimal[].class)));
		assertThat(Arrays.equals(returned, decimals), is(true));
	}
}
