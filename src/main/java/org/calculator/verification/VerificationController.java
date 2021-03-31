package org.calculator.verification;

import org.calculator.user.Ui;

public class VerificationController implements VerificationBoundary {

	public InputVerifier verifier(Ui gui) {
		InputValidator validator = new InputValidator();
		return new InputVerifier(
				validator,
				new ErrorSender(gui));
	}
}
