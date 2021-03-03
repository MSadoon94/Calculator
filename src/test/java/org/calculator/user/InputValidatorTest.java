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
		assertThat(validator.isValidInput("1/0"), is(false));
	}
	@Test
	void shouldReturnInvalidIfInputIsNonOperationCharacter(){
		assertThat(validator.isValidInput("#"), is(false));
		assertThat(validator.isValidInput("`"), is(false));
		assertThat(validator.isValidInput("?"), is(false));
		assertThat(validator.isValidInput("4t"), is(false));
	}
	@Test
	void shouldReturnInputThatIsInvalid(){
		validator.isValidInput("A");
		assertThat(validator.invalidInput(), is(equalTo("A")));
	}
	@Test
	void shouldReturnInvalidIfDecimalPositionIsNotInteger(){
		assertThat(validator.isValidDecimalPosition("A"), is(false));
		assertThat(validator.isValidDecimalPosition("."), is(false));
		assertThat(validator.isValidDecimalPosition("4t"), is(false));
		assertThat(validator.isValidDecimalPosition("+"), is(false));
	}


}
