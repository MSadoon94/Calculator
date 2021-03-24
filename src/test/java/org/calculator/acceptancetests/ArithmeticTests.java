package org.calculator.acceptancetests;

import org.calculator.common.TestHelper;
import org.calculator.control.ComponentCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ArithmeticTests {

	private static HashMap<String, String> answers = new HashMap<>();

	protected JFrameOperator frame;
	protected JTextAreaOperator textArea;
	protected JButtonOperator equalsButton, decimalPositionButton;

	private String input = "0";

	@BeforeEach
	void setUp(){
		new ComponentCreator();
		setAnswers();
	}

	@DisplayName("Arithmetic Tests")
	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"WhenNumberIsEntered_ThenThatSameNumberWillBeReturned, 2.0",
			"WhenUserRequestsAddition_ThenInputtedValuesAreAdded, 2+2",
			"WhenUserRequestsSubtraction_ThenInputtedValuesAreSubtracted, 4-2",
			"WhenUserRequestsMultiplication_ThenInputtedValuesAreMultiplied, 2*2",
			"WhenUserRequestsDivision_ThenInputtedValuesAreDivided, 4/2",
			"WhenUserRequestsPercentage_ThenInputtedValueIsTurnedIntoPercentages, 0.5%",
			"WhenUserRequestsMixedArithmetic_ThenOperationsAreAppliedToGroupedInputs, (2-1+1*2)/(10/5)",
			"WhenUserRequestsHaveExponents_ThenTheValuesWillBeUsedInExponentiation, 2^3",
			"WhenUserRequestsHaveSquareRoot_ThenTheValueWillBeSquareRooted, √4",
			"WhenUserRequestsHaveNegativeNumbers_ThenProcessingWillTakeIntoAccountTheNegative, 4+-2",
	})
	void testingFixture(String test, String aInput){
		//Test variable is used to report name in test results
		input = aInput;
		setOperators();
		startInputtingRequest();
		hasDisplayedAnswer();
		frame.getWindow().dispose();
	}

	private void setOperators(){
		frame = new JFrameOperator();
		textArea = new JTextAreaOperator(frame);
		equalsButton = new JButtonOperator(frame, "=");
	}

	private void setAnswers(){
		TestHelper[] types = TestHelper.values();
		for (TestHelper type : types) {
			answers.put(type.input(), type.answer());
		}

	}

	private void startInputtingRequest(){
		textArea.enterText(input);
		equalsButton.push();
	}

	private void hasDisplayedAnswer() {
		assertThat(textArea.getText().trim(), is(equalTo(answers.get(input))));
	}
}
