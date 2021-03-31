package org.calculator.verification;

import org.calculator.user.Ui;

public class VerificationController implements VerificationBoundary {

	public ErrorSender errorSender(Ui aGui) {
		return new ErrorSender(new InputValidator(), aGui);
	}

	public InputVerifier verifier(Ui gui) {
		InputValidator validator = new InputValidator();
		return new InputVerifier(
				validator,
				new ErrorSender(validator, gui));
	}
}
