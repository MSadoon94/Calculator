package org.calculator.user;

import org.calculator.common.Request;

class ErrorMessenger {
	private InputValidator validator;
	private Gui gui;

	public ErrorMessenger(InputValidator aValidator, Gui aGui){
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
}
