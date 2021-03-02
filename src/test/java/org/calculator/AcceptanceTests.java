package org.calculator;

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

public class AcceptanceTests {

	private static HashMap<String, String> answers = new HashMap<>();

	private JFrameOperator frameOperator;
	private JTextAreaOperator textAreaOperator;
	private JButtonOperator equalsButton;

	private String input = "0";

	@BeforeEach
	void setUp(){
		new ComponentCreator();
		setAnswers();
	}

	@DisplayName("TestResults")
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
			"WhenUserSpecifiesDecimalPosition_ThenResultWillHaveDecimalInSpecifiedPosition, 4.5234*5.4325"
	})
	void testingFixture(String test, String aInput){
		//Although unused, test variable is needed so test name isn't assigned to input.
		input = aInput;
		setOperators();
		startInputtingRequest();
		hasDisplayedAnswer();
		frameOperator.getWindow().dispose();
		
	}

	private void setOperators(){
		frameOperator = new JFrameOperator("Gui");
		textAreaOperator = new JTextAreaOperator(frameOperator, 0);
		equalsButton = new JButtonOperator(frameOperator, "=");
	}

	private void setAnswers(){
		TestHelper[] types = TestHelper.values();
		for (TestHelper type : types) {
			answers.put(type.input(), type.answer());
		}

	}

	private void startInputtingRequest(){
		textAreaOperator.enterText(input);
		equalsButton.push();

	}

	private void hasDisplayedAnswer() {
		assertThat(textAreaOperator.getText().trim(), is(equalTo(answers.get(input))));
	}
}
