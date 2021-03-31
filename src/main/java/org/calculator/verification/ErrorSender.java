package org.calculator.verification;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.user.Ui;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ErrorSender implements Verifier {
	private InputValidator validator;
	private Ui gui;

	public ErrorSender(InputValidator aValidator, Ui aGui){
		validator = aValidator;
		gui = aGui;
	}

	public Request checkedInput(String input){
		Request result = validate(input);
		if (result.input().contains(Operations.ROOT.symbol())){
			result = rootOperation(result);
		}
		return result;
	}

	public int checkDecimalInput(String input) {
		int position;
		if (validator.isValidDecimalPosition(input)) {
			position = Integer.parseInt(input);
		} else {
			gui.decimalPositionError();
			position = 2;
		}
		return position;
	}

	public Request validate(String input){
		Request result;
		if (validator.isValidInput(input)){
			result = new Request(input);
		} else {
			gui.inputErrorMessage(validator.invalidInput());
			result = new Request("Invalid Input");
		}
		return result;
	}

	public void sendInputError(InputValidator validator){
		gui.inputErrorMessage(validator.invalidInput());
	}


	private Request rootOperation(Request aRequest){
		Matcher rootMatch = Pattern.compile("\\d[√]\\d").matcher(aRequest.input());
		Matcher sqrtMatch = Pattern.compile("[√]\\d").matcher(aRequest.input());
		if(!rootMatch.find()){
			aRequest = appendedRequest(aRequest, sqrtMatch);
		}
		return aRequest;
	}

	private Request appendedRequest(Request aRequest, Matcher sqrtMatch){
		sqrtMatch.find();
		String replacement = "2" + sqrtMatch.group();
		aRequest = new Request(aRequest.input().replace(sqrtMatch.group(), replacement));
		aRequest.setOperation(Operations.ROOT);
		return aRequest;
	}
}

