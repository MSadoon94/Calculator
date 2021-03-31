package org.calculator.verification;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InputVerifierTest {

	@Mock InputValidator validator;
	@Mock ErrorSender errorSender;

	@Test
	void shouldAppend2ToRootsWithoutNumberInFront(){
		String input = TestHelper.SQUARE_ROOT.input();
		when(validator.isValidInput(input)).thenReturn(true);

		InputVerifier verifier = new InputVerifier(validator, errorSender);

		assertThat(verifier.verifiedInput(input).input(), is("2√4"));
	}

	@Test
	void shouldAppendMultiplicationBetweenAdjacentParenthesis(){
		String input = "(2)(2)";
		when(validator.isValidInput(input)).thenReturn(true);

		InputVerifier verifier = new InputVerifier(validator, errorSender);

		assertThat(verifier.verifiedInput(input).input(), is("(2)*(2)"));
	}
}
