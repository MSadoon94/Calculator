package org.calculator.verification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputValidator {
	private Matcher matcher;
	public boolean isValidInput(String input) {
		matcher = Pattern.compile(invalidGroups())
				.matcher(input);

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

	private String invalidGroups(){
		String divisionByZero = "[\\d]+/0";
		String invalidCharacters = "[^\\d\\s+\\-*/%().^√]+";
		String invalidSequences = "(" + "[+\\-*/%.^√]+" + "[+*/%.^√]+" + ")+";
		String multipleDecimals = "(" + "\\d*[.]" + "){2}";
		String emptyParenthesis = "\\(\\)";

		return divisionByZero
				+ "|" + invalidCharacters
				+ "|" + invalidSequences
				+ "|" + multipleDecimals
				+ "|" + emptyParenthesis;
	}
}
