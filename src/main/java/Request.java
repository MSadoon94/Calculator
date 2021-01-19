public class Request extends Calculation {
	private double[] additions;
	private double[] subtractions;
	public Request(String request) {
		super(request);
	}

	public double[] getAdditions() {
		return additions;
	}

	public double[] getSubtractions(){return subtractions;}

	public void setAdditions(double[] addList) {
		additions = addList;
	}

	public void setSubtractions(double[] minusList) { subtractions = minusList;}

}
