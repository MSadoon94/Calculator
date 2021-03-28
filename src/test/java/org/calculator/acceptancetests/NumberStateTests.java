package org.calculator.acceptancetests;

import org.calculator.common.TestHelper;
import org.calculator.control.ComponentCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class NumberStateTests {

		private static HashMap<String, String> answers = new HashMap<>();

		private JFrameOperator frame;
		private JComponentOperator entryPanel;
		private JTextFieldOperator textField;
		private JButtonOperator equalsButton, decimalPositionButton, notationButton;

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
			textField.enterText(String.valueOf(decimalPosition()));
			decimalPositionButton.push();
			textField.enterText(input);
			equalsButton.push();
			hasDisplayedAnswer();
		}

		@Test
		void WhenUserRequestsScientificNotation_ThenResultWillBeConvertedToScientificNotation(){
			input = TestHelper.NOTATION.input();
			textField.enterText(input);
			notationButton.push();
			hasDisplayedAnswer();
		}


		private void setOperators(){
			frame = new JFrameOperator();
			entryPanel = new JComponentOperator(frame, new NameComponentChooser("Entry Panel"), 0);
			textField = new JTextFieldOperator(entryPanel);
			equalsButton = new JButtonOperator(entryPanel, "=");
			decimalPositionButton = new JButtonOperator(entryPanel, "Decimal Position");
			notationButton = new JButtonOperator(entryPanel, "Notation");
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
			assertThat(textField.getText().trim(), is(equalTo(answers.get(input))));
		}
}
