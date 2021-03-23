package org.calculator.verification;

import org.calculator.user.Ui;

public interface VerificationBoundary {
	ErrorSender errorSender(Ui aGui);
}
