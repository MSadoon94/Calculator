package org.calculator.acceptancetests;

import org.calculator.common.TestHelper;
import org.calculator.control.ComponentCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
			setOperators();
		}

		@Test
		void WhenUserSpecifiesDecimalPosition_ThenResultWillHaveDecimalInSpecifiedPosition(){
			input = TestHelper.DECIMAL.input();
			textArea.enterText(String.valueOf(decimalPosition()));
			decimalPositionButton.push();
			textArea.enterText(input);
			equalsButton.push();
			hasDisplayedAnswer();
		}

		@Test
		void WhenUserRequestsScientificNotation_ThenResultWillBeConvertedToScientificNotation(){
			input = TestHelper.NOTATION.input();
			textArea.enterText(input);
			notationButton.push();
			hasDisplayedAnswer();
		}


		private void setOperators(){
			frame = new JFrameOperator();
			textArea = new JTextAreaOperator(frame);
			equalsButton = new JButtonOperator(frame, "=");
			decimalPositionButton = new JButtonOperator(frame, "Decimal Position");
			notationButton = new JButtonOperator(frame, "Notation");
		}

		private void setAnswers(){
			TestHelper[] types = TestHelper.values();
			for (TestHelper type : types) {
				answers.put(type.input(), type.answer());
			}

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
