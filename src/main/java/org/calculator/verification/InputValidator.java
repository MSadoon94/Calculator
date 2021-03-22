package org.calculator.verification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputValidator {
	private Matcher matcher;
	public boolean isValidInput(String input) {
		String divisionByZero = "[\\d]+/0";
		String validCharacters = "[^\\d\\s+\\-*/%().^√]+";
		matcher = Pattern.compile(divisionByZero + "|" + validCharacters).matcher(input);
		return !matcher.find();
	}

	public String invalidInput() {
		return matcher.group();
	}

	public boolean isValidDecimalPosition(String position){
		Matcher nonInteger = Pattern.compile("[^\\d\\s]+").matcher(position);
		boolean isInputValid = !nonInteger.find();
		return isInputValid;
	}
}
