package org.calculator.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	private Matcher matcher;
	public boolean isValid(String input) {
		this.matcher = Pattern.compile("[A-Za-z]").matcher(input);
		boolean isValid = !matcher.find();
		return isValid;
	}

	public String invalidInput() {
		return matcher.group();
	}
}
