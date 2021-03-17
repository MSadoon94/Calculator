package org.calculator.acceptancetests;

import org.calculator.control.ComponentCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.netbeans.jemmy.operators.*;
import org.netbeans.jemmy.util.NameComponentChooser;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HistoryTests{
	private JFrameOperator frame;
	private JComponentOperator inputPanel, answerPanel;
	private JTextAreaOperator textArea;
	private JButtonOperator equalsButton, decimalPositionButton,
			inputBackButton, inputNextButton, answerBackButton, answerNextButton;
	private JTextFieldOperator inputTextField, answerTextField;

	@BeforeEach
	void setUp(){
		new ComponentCreator();
	}

	@DisplayName("History Tests")
	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"WhenUserInputsMultipleRequests_ThenThePreviousInputsWillBeAccessible",
			"WhenUserInputsMultipleRequests_ThenThePreviousAnswerWillBeAccessible"
	})
	void historyTestingFixture(String test){
		//Test variable is used to report name in test results
		setOperators();
		startInputtingRequest("0+1");
		for (int i = 1; i < 5; i++){
			int offset = i-1;
			startInputtingRequest(i + "+1");
			hasDisplayedInputHistory(offset  + "+1");
			hasDisplayedAnswerHistory(offset + 1);
		}
		frame.getWindow().dispose();
	}

	private void setOperators() {
		frame = new JFrameOperator();
		setPanels();
		setTextFields();
		setButtons();
	}

	private void setPanels(){
		inputPanel = new JComponentOperator(frame, new NameComponentChooser("InputHistory"), 0);
		answerPanel = new JComponentOperator(frame, new NameComponentChooser("AnswerHistory"), 0);
	}

	private void setButtons(){
		equalsButton = new JButtonOperator(frame, "=");
		decimalPositionButton = new JButtonOperator(frame, "Decimal Position");
		inputBackButton = new JButtonOperator(inputPanel, "<", 0);
		inputNextButton = new JButtonOperator(inputPanel, ">", 0);
		answerBackButton = new JButtonOperator(answerPanel, "<", 0);
		answerNextButton = new JButtonOperator(answerPanel, ">", 0);
	}

	private void setTextFields(){
		textArea = new JTextAreaOperator(frame);
		inputTextField = new JTextFieldOperator(inputPanel);
		answerTextField = new JTextFieldOperator(answerPanel);
	}

	private void startInputtingRequest(String input){
			textArea.enterText(input);
			equalsButton.push();
			inputBackButton.push();
			answerBackButton.push();
	}

	private void hasDisplayedInputHistory(String input){
		assertThat(inputTextField.getText().trim(), is(equalTo(input)));
	}

	private void hasDisplayedAnswerHistory(int answer){
			BigDecimal value = BigDecimal.valueOf(answer);
			String result = value.setScale(2, RoundingMode.HALF_UP).toString();
			assertThat(answerTextField.getText().trim(), is(equalTo(result)));
	}
}
