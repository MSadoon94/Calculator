package org.calculator.verification;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.user.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			result = requestAfterRootCheck(new Request(input));
		} else {
			errorSender.sendInputError(validator);
			result = new Request("Invalid Input");
		}
		return result;
	}

	private Request requestAfterRootCheck(Request request){
		if(request.input().contains(Operations.ROOT.symbol())){
			request = rootOperation(request);
		}
		return request;
	}

	private Request rootOperation(Request aRequest){
		Matcher rootMatch = Pattern.compile("\\d[√]\\d").matcher(aRequest.input());
		Matcher sqrtMatch = Pattern.compile("[√]\\d").matcher(aRequest.input());
		if(!rootMatch.find()){
			aRequest = appendedRoot(aRequest, sqrtMatch);
		}
		return aRequest;
	}

	private Request appendedRoot(Request aRequest, Matcher sqrtMatch){
		sqrtMatch.find();
		String replacement = "2" + sqrtMatch.group();
		aRequest = new Request(aRequest.input().replace(sqrtMatch.group(), replacement));
		aRequest.setOperation(Operations.ROOT);
		return aRequest;
	}
}
