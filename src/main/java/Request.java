import java.util.ArrayList;

public class Request extends Calculation {
	private ArrayList<String> additions;
	public Request(String request) {
		super(request);
	}

	public ArrayList<String> getAdditions() {
		return additions;
	}

	public void setAdditions(ArrayList<String> addList) {
		additions = addList;
	}
}
