import java.util.ArrayList;

public class AnalyzedRequest extends Request {
	private ArrayList<String> additions;
	public AnalyzedRequest(String request) {
		super(request);
	}

	public ArrayList<String> getAdditions() {
		return additions;
	}

	public void setAdditions(ArrayList<String> addList) {
		additions = addList;
	}
}
