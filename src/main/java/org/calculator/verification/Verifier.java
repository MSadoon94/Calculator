package org.calculator.verification;

import org.calculator.common.Request;

public interface Verifier {

	Request verifiedInput(String input);

	int verifiedDecimal(String position);

}
