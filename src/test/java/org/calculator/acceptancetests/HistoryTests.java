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
	private JTextFieldOperator inputHistoryTextField;

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
		startInputtingRequest("0+1");
		for (int i = 1; i < 5; i++){
			int offset = i-1;
			startInputtingRequest(i + "+1");
			hasDisplayedHistory(offset  + "+1");
		}
		frame.getWindow().dispose();
	}

	private void setOperators() {
		frame = jFrameOperator();
		textArea = jTextAreaOperator(frame);
		equalsButton = jButtonOperator(frame, "=");
		decimalPositionButton = jButtonOperator(frame, "Decimal Position");
		inputHistoryTextField = new JTextFieldOperator(frame, 0);
		historyBackButton = new JButtonOperator(frame, "Back", 0);
		historyNextButton = new JButtonOperator(frame, "Next", 0);
	}

	private void startInputtingRequest(String input){
			textArea.enterText(input);
			equalsButton.push();
			historyBackButton.push();
	}

	private void hasDisplayedHistory(String input){
		assertThat(inputHistoryTextField.getText().trim(), is(equalTo(input)));
	}
}
