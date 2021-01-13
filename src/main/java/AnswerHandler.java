public class AnswerHandler implements AnswerServices {
	private AnswerCache cache = new AnswerCache();

	public Answer getAnswer() {
		return cache.getNextAnswer();
	}

	public void addAnswer(String answer) {
		cache.addAnswer(new Answer(answer));
	}
}
