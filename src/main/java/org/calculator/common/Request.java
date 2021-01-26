package org.calculator.common;

import java.util.Arrays;
import java.util.HashMap;

public class Request extends Calculation {
	private HashMap<Operations, double[]> sectionType = new HashMap<>();

	public Request(String request) {
		super(request);
	}
	public void setSection(Operations type, double[] values){
		boolean typeIsFunctionOperation = Arrays.stream(Operations.functionOps()).anyMatch(op -> op == type);
		if (typeIsFunctionOperation) {
			double[] value = {Double.parseDouble(this.toString())};
			sectionType.put(type,value);
		}
		sectionType.put(type,values);
	}

	public double[] getSection(Operations type){
		return sectionType.get(type);
	}

}
