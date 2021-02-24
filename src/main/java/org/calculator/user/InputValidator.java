package org.calculator.user;

public class InputValidator {
	public boolean isValid(String input) {
		boolean isValid = !input.matches("[A-Za-z]");
		return isValid;
	}
}
