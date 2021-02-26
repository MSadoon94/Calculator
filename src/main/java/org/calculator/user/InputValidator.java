package org.calculator.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	private Matcher matcher;
	public boolean isValid(String input) {

		String divisionByZero = "[\\d]+/0";
		String validCharacters = "[^\\d\\s+\\-*/%().^√]+";
		this.matcher = Pattern.compile(divisionByZero + "|" + validCharacters).matcher(input);
		return !matcher.find();
	}

	public String invalidInput() {
		return matcher.group();
	}
}
