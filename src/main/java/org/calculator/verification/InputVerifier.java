package org.calculator.verification;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputVerifier implements Verifier{
	private InputValidator validator;
	private ErrorSender errorSender;
	public InputVerifier(InputValidator aValidator, ErrorSender aErrorSender){
		validator = aValidator;
		errorSender = aErrorSender;
	}

	public Request verifiedInput(String input){
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

	public int verifiedDecimal(String input) {
		int position;
		if (validator.isValidDecimalPosition(input)) {
			position = Integer.parseInt(input);
		} else {
			errorSender.sendDecimalError();
			position = 2;
		}
		return position;
	}
}
