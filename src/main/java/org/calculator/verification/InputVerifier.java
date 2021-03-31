package org.calculator.verification;

import org.calculator.common.Request;
import org.calculator.user.Ui;

class InputVerifier {
	private InputValidator validator;
	private ErrorSender errorSender;
	public InputVerifier(InputValidator aValidator, ErrorSender aErrorSender,Ui aGui){
		validator = aValidator;
		errorSender = aErrorSender;
	}

	public Request verified(String input){
		Request result;
		if (validator.isValidInput(input)){
			result = new Request(input);
		} else {
			errorSender.sendInputError(validator);
			result = new Request("Invalid Input");
		}
		return result;
	}
}
