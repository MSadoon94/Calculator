package org.calculator.verification;

import org.calculator.common.Request;
import org.calculator.user.Ui;

class ErrorSender implements Verifier {
	private InputValidator validator;
	private Ui gui;

	public ErrorSender(InputValidator aValidator, Ui aGui){
		validator = aValidator;
		gui = aGui;
	}

	public Request checkedInput(String input){
		Request result;
		if (validator.isValidInput(input)){
			result = new Request(input);
		} else {
			gui.inputErrorMessage(validator.invalidInput());
			result = new Request("Invalid Input");
		}
		return result;
	}

	public int checkDecimalInput(String input){
		int position;
		if(!validator.isValidDecimalPosition(input)){
			gui.decimalPositionError();
			position = 2;
		} else {
			position = Integer.parseInt(input);
		}
		return position;
	}
}
