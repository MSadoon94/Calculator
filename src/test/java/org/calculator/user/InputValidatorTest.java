package org.calculator.user;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class InputValidatorTest {

	@Test
	void shouldReturnInvalidIfInputContainsAlphabeticValue(){
		InputValidator validator = new InputValidator();
		assertThat(validator.isValid("A"), is(false));
		assertThat(validator.isValid("a"), is(false));
	}

}
