package org.calculator.processortests;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.HashMap;

public class ProcessorTestHelper {
	private HashMap<String, String> answers = new HashMap<>();
	private String[] type = {"SingleValue", "Addition", "Subtraction", "Multiplication"};
	private String input;
	private Request request;

	public void setTestTypeFields(String test){
		if (test.equals(type[0])){
			setForSingleValue();
		} else if (test.equals(type[1])){
			setForAdditionValue();
		} else if (test.equals(type[2])){
			setForSubtractionValue();
		} else if (test.equals(type[3])){
			setForMultiplicationValue();
		}
	}
	public Request getRequest(){
		return request;
	}
	public String getAnswer(String type){
		return answers.get(type);
	}
	private void setForSingleValue(){
		input = "2.0";
		request = new Request(input);
		answers.put(type[0], "2.0");
	}
	private void setForAdditionValue(){
		input = "2+2";
		request = new Request(input);
		request.setSection(Operations.ADDITION, new double[]{2.0, 2.0});
		answers.put(type[1], "4.0");
	}
	private void setForSubtractionValue(){
		input = "4-2";
		request = new Request(input);
		request.setSection(Operations.SUBTRACTION, new double[]{4.0, 2.0});
		answers.put(type[2], "2.0");
	}
	private void setForMultiplicationValue(){
		input = "2*2";
		request = new Request(input);
		request.setSection(Operations.MULTIPLICATION, new double[]{2.0, 2.0});
		answers.put(type[3], "4.0");
	}

}
