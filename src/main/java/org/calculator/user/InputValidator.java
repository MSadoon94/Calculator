package org.calculator.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	private Matcher matcher;
	public boolean isValid(String input) {
		String letters = "[A-Za-z]";
		String divisionByZero = "[\\d]+/0";
		this.matcher = Pattern.compile(letters + "|" + divisionByZero).matcher(input);
		boolean isValid = !matcher.find();
		return isValid;
	}

	public String invalidInput() {
		return matcher.group();
	}
}
