package org.calculator.verification;

import org.calculator.user.Ui;

public class VerificationController implements VerificationBoundary {

	public ErrorSender errorSender(Ui aGui) {
		return new ErrorSender(new InputValidator(), aGui);
	}
}
