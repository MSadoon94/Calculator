package org.calculator.user;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class InputValidatorTest {

	@Test
	void shouldReturnInvalidIfInputContainsLetterValue(){
		InputValidator validator = new InputValidator();
		assertThat(validator.isValid("A"), is(false));
		assertThat(validator.isValid("a"), is(false));
	}

	@Test
	void shouldReturnInputThatIsInvalid(){
		InputValidator validator = new InputValidator();
		validator.isValid("A");
		assertThat(validator.invalidInput(), is(equalTo("A")));
	}

}
