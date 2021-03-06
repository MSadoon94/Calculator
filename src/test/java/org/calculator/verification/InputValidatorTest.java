package org.calculator.verification;
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
		assertThat(validator.isValidDecimalPosition("3.1"), is(false));
		assertThat(validator.isValidDecimalPosition("4t"), is(false));
		assertThat(validator.isValidDecimalPosition("+"), is(false));
	}
	@Test
	void shouldReturnInvalidIfInputContainsInvalidOperationSequences(){
		assertThat(validator.isValidInput("2++2"), is(false));
		assertThat(validator.isValidInput("2+-+2"), is(false));
		assertThat(validator.isValidInput("2**2"), is(false));
	}

	@Test
	void shouldReturnValidIfInputHasNegativeNumber(){
		assertThat(validator.isValidInput("2+-2"), is(true));
	}

	@Test
	void shouldReturnInvalidIfInputContainsNumberWithMultipleDecimalPoints(){
		assertThat(validator.isValidInput("2.03.20"), is(false));
		assertThat(validator.isValidInput("103.4156.13"), is(false));
		assertThat(validator.isValidInput(".215.1546.0"), is(false));
	}

	@Test
	void shouldReturnInvalidIfInputContainsEmptyParenthesis(){
		assertThat(validator.isValidInput("()+2"), is(false));
	}


}
