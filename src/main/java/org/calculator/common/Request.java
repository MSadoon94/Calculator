package org.calculator.common;

import java.math.BigDecimal;
import java.util.List;

public class Request extends RequestUtility{

	protected final String input;
	protected Operations operation;
	private String innerGroup;

	public Request(String request) {
		super();
		this.input = request;
	}

	public String input(){
		return input;
	}

	public void setOperation(Operations operation){
		this.operation = operation;
	}

	public Operations getOperation(){
		return operation;
	}

	public void setInnerGroup(String innerGroup){
		this.innerGroup = innerGroup;
	}

	public String getInnerGroup(){
		return innerGroup;
	}

	public long operatorAmount(){
		return amountOfOperators(this);
	}

	public BigDecimal[] bigDecimals(){
		return bigDecimalValues(this);
	}

	public List<Operations> operators(){
		return operatorList(this);
	}

}
