package org.calculator.verification;

import org.calculator.common.Request;

public interface Verifier {

	Request checkedInput(String input);

	int checkDecimalInput(String position);

}
