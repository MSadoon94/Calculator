package org.calculator.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class InputValidatorTest {
	private InputValidator validator;

	@BeforeEach
	void setUp(){
		validator = new InputValidator();
	}
	@Test
	void shouldReturnInvalidIfInputHasDivisionByZero(){
		assertThat(validator.isValid("1/0"), is(false));
	}
	@Test
	void shouldReturnInvalidIfInputIsNonOperationCharacter(){
		assertThat(validator.isValid("#"), is(false));
		assertThat(validator.isValid("`"), is(false));
		assertThat(validator.isValid("?"), is(false));
	}
	@Test
	void shouldReturnInputThatIsInvalid(){
		validator.isValid("A");
		assertThat(validator.invalidInput(), is(equalTo("A")));
	}


}
