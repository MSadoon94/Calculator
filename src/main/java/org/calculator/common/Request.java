package org.calculator.common;

import java.util.HashMap;

public class Request extends Calculation {
	private HashMap<Operations, double[]> sectionType = new HashMap<>();

	public Request(String request) {
		super(request);
	}
	public void setSection(Operations type, double[] values){
		sectionType.put(type,values);
	}

	public double[] getSection(Operations type){
		return sectionType.get(type);
	}

}
