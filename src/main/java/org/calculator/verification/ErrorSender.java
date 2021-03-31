package org.calculator.verification;

import org.calculator.user.Ui;


class ErrorSender {
	private Ui gui;

	public ErrorSender(Ui aGui){
		gui = aGui;
	}

	public void sendInputError(InputValidator validator){
		gui.inputErrorMessage(validator.invalidInput());
	}

	public void sendDecimalError(){
		gui.decimalPositionError();
	}
}

