package org.calculator.verification;
import org.calculator.common.TestHelper;
import org.calculator.user.Ui;
import org.calculator.user.UserBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ErrorSenderTest {

	@Mock
	InputValidator validator;

	@Mock
	Ui gui;

	@Test
	void shouldAppend2ToRootsWithoutNumberInFront(){
		String input = TestHelper.SQUARE_ROOT.input();
		when(validator.isValidInput(input)).thenReturn(true);


		ErrorSender sender = new ErrorSender(validator, gui );

		assertThat(sender.checkedInput(input).input(), is("2√4"));
	}
}
