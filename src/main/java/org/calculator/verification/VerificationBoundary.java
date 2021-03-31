package org.calculator.verification;

import org.calculator.user.Ui;

public interface VerificationBoundary {
	InputVerifier verifier(Ui aGui);
}
