package org.calculator.verification;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.user.Ui;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ErrorSender {
	private InputValidator validator;
	private Ui gui;

	public ErrorSender(InputValidator aValidator, Ui aGui){
		validator = aValidator;
		gui = aGui;
	}

	public void sendInputError(InputValidator validator){
		gui.inputErrorMessage(validator.invalidInput());
	}

	public void sendDecimalError(){
		gui.decimalPositionError();
	}
}

