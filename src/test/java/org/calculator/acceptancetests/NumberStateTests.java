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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class NumberStateTests {

		private static HashMap<String, String> answers = new HashMap<>();

		protected JFrameOperator frame;
		protected JTextAreaOperator textArea;
		protected JButtonOperator equalsButton, decimalPositionButton, notationButton;

		private String input = "0";

		@BeforeEach
		void setUp(){
			new ComponentCreator();
			setAnswers();
		}

		@DisplayName("Number State Tests")
		@ParameterizedTest(name = "{index} ==> {0}")
		@CsvSource({
				"WhenUserSpecifiesDecimalPosition_ThenResultWillHaveDecimalInSpecifiedPosition, 4.5234",
				"WhenUserSpecifiesScientificNotation_ThenResultWillBeConvertedToScientificNotation, 6543.21"
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
			decimalPositionButton = new JButtonOperator(frame, "Decimal Position");
			notationButton = new JButtonOperator(frame, "Scientific Notation");
		}

		private void setAnswers(){
			TestHelper[] types = TestHelper.values();
			for (TestHelper type : types) {
				answers.put(type.input(), type.answer());
			}

		}

		protected void startInputtingRequest(){
			textArea.enterText(String.valueOf(decimalPosition()));
			decimalPositionButton.push();
			textArea.enterText(input);
			equalsButton.push();
		}


		private int decimalPosition(){
			int position = 2;
			if(input.equals(TestHelper.DECIMAL.input())){
				position = 3;
			}
			return position;
		}

		private void hasDisplayedAnswer() {
			assertThat(textArea.getText().trim(), is(equalTo(answers.get(input))));
		}
}
