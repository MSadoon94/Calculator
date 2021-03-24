package org.calculator.processing;
import org.calculator.common.Request;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class NotationConverterTest {

	@Test
	void shouldCountAmountOfNumbersBeforeOrAfterFirstDigit(){
		NotationConverter notation = new NotationConverter();
		Request request = new Request("2431");

		Request result = notation.scientific(request);

		assertThat(result.input(), containsString("3"));
	}

}
