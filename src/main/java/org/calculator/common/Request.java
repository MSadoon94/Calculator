package org.calculator.common;

import java.util.HashMap;

public class Request extends Calculation {
	private HashMap<String, double[]> sectionType = new HashMap<>();

	public Request(String request) {
		super(request);
	}
	public void setSection(String type, double[] values){
		sectionType.put(type,values);
	}

	public double[] getSection(String type){
		return sectionType.get(type);
	}

}
