package org.calculator.verification;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.calculator.user.Ui;
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
	@Mock Ui gui;

	@Test
	void shouldReturnRequestWithVerifiedInput(){
		String input = TestHelper.SQUARE_ROOT.input();
		when(validator.isValidInput(input)).thenReturn(true);


		InputVerifier verifier = new InputVerifier(validator, errorSender, gui);
		Request result = verifier.verified(TestHelper.SQUARE_ROOT.input());

		assertThat(result.input(), is(TestHelper.SQUARE_ROOT.input()));
	}
}
