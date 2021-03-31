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
			result = appendedRequest(new Request(input));
		} else {
			errorSender.sendInputError(validator);
			result = new Request("Invalid Input");
		}
		return result;
	}

	private Request appendedRequest(Request request){
		if(request.input().contains(Operations.ROOT.symbol())){
			request = rootOperation(request);
		}
		if(request.input().contains(")")){
			request = doubleParenthesis(request);
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

	private Request doubleParenthesis(Request request){
		Matcher doubleParenthesis = Pattern.compile("\\)[\\d+(]|[\\d+)]\\(").matcher(request.input());
		if(doubleParenthesis.find()){
			String replacement = doubleParenthesis.group().replace(")", ")*");
			if (!replacement.matches("\\)\\*\\(")){
				replacement = replacement.replace("(", "*(");
			}
			request = new Request(request.input().replace(doubleParenthesis.group(), replacement));
		}
		return request;
	}
}
