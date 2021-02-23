package org.calculator.processing;
import org.calculator.common.Request;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class NegativeFormatterTest {

	@Test
	void shouldReplaceNegativeNumbersWithNumberThatHasNegativeTag(){
		NegativeFormatter formatter = new NegativeFormatter();
		Request taggedRequest = formatter.tagNegatives(new Request("2+-2"));

		assertThat(taggedRequest.value(), is("2+NEG2"));
	}
}
