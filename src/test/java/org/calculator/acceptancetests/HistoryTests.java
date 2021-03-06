package org.calculator.acceptancetests;

import org.calculator.control.ComponentCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HistoryTests extends AcceptanceTests{
	private JTextFieldOperator inputTextFieldOperator;
	private JButtonOperator historyBackButton, historyNextButton;

	/*@BeforeEach
void setUp(){
	new ComponentCreator();
	setAnswers();
}
 */
	@DisplayName("HistoryTests")
	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"WhenUserInputsMultipleRequests_ThenThePreviousInputsWillBeAccessible"
	})
	void historyTestingFixture(){
		setOperators();
		startInputtingHistoryRequests();
		hasDisplayedHistory();
		frameOperator.getWindow().dispose();
	}

	protected void setOperators() {
		super.setOperators();
		inputTextFieldOperator = new JTextFieldOperator(frameOperator, "inputHistoryTextField");
		historyBackButton = new JButtonOperator(frameOperator, "Back", 0);
		historyNextButton = new JButtonOperator(frameOperator, "Next", 0);
	}

	private void startInputtingHistoryRequests(){
		for (int i = 0; i < 2; i++) {
			textAreaOperator.enterText(i + "+1");
			equalsButton.push();
		}
	}

	private void hasDisplayedHistory(){
		for (int i = 0; i < 2; i++) {
			assertThat(inputTextFieldOperator.getText().trim(), is(equalTo(i+"i")));
		}
	}
}
