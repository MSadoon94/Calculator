public class Request extends Calculation {
	private String[] additions;
	private String[] subtractions;
	public Request(String request) {
		super(request);
	}

	public String[] getAdditions() {
		return additions;
	}

	public String[] getSubtractions(){return subtractions;}

	public void setAdditions(String[] addList) {
		additions = addList;
	}

	public void setSubtractions(String[] minusList) { subtractions = minusList;}

}
