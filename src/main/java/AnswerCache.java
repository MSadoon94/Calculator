import java.util.ArrayList;

public class AnswerCache {
	private ArrayList<Answer> answers = new ArrayList<>();

	public void addAnswer(Answer in) {
		answers.add(in);
	}

	public Answer getNextAnswer() {
		return answers.get(0);
	}
}
