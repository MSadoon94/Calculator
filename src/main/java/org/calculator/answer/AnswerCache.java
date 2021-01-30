package org.calculator.answer;

import org.calculator.common.Answer;
import java.util.ArrayList;

class AnswerCache {
	private ArrayList<Answer> answers = new ArrayList<>();

	public void addAnswer(Answer in) {
		answers.add(in);
	}

	public Answer getNextAnswer() {
		return answers.get(0);
	}
}
