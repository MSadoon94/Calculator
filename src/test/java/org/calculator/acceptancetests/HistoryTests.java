package org.calculator.acceptancetests;

import org.calculator.control.ComponentCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HistoryTests extends AcceptanceTestFixture {
	private JFrameOperator frame;
	private JTextAreaOperator textArea;
	private JButtonOperator equalsButton, decimalPositionButton,
			historyBackButton, historyNextButton;
	private JTextFieldOperator inputTextFieldOperator;

	@BeforeEach
	void setUp(){
		new ComponentCreator();
	}

	@DisplayName("HistoryTests")
	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"WhenUserInputsMultipleRequests_ThenThePreviousInputsWillBeAccessible"
	})
	void historyTestingFixture(){
		setOperators();
		startInputtingRequest();
		hasDisplayedHistory();
		frame.getWindow().dispose();
	}

	protected void setOperators() {
		frame = jFrameOperator();
		textArea = jTextAreaOperator(frame);
		equalsButton = jButtonOperator(frame, "=");
		decimalPositionButton = jButtonOperator(frame, "Decimal Position");
		inputTextFieldOperator = new JTextFieldOperator(frame, 1);
		historyBackButton = new JButtonOperator(frame, "Back", 0);
		historyNextButton = new JButtonOperator(frame, "Next", 0);
	}

	protected void startInputtingRequest(){
		for (int i = 0; i < 2; i++) {
			textArea.enterText(i + "+1");
			equalsButton.push();
		}
	}

	private void hasDisplayedHistory(){
		for (int i = 0; i < 2; i++) {
			assertThat(inputTextFieldOperator.getText().trim(), is(equalTo(i+"+1")));
		}
	}
}
