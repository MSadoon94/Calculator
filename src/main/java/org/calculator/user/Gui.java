package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class Gui implements ActionListener, UiActions {

	private Observer reqObserver;
	private JPanel mainPanel;
	private JTextArea mainTextArea;
	private JButton equalsButton, addButton, subtractButton, divideButton,
			multiplyButton, percentageButton, clearButton, decimalButton,
			rightParenthesisButton, leftParenthesisButton, exponentButton,
			a0Button, a1Button, a2Button, a3Button, a4Button, a5Button,
			a6Button, a7Button, a8Button, a9Button;
	private JButton[] numericalButtons = {
			a0Button, a1Button, a2Button, a3Button, a4Button,
			a5Button, a6Button, a7Button, a8Button, a9Button,
			decimalButton
	};
	private JButton[] operationButtons = {
			equalsButton, addButton, subtractButton, divideButton,
			multiplyButton, percentageButton, clearButton,
			rightParenthesisButton, leftParenthesisButton, exponentButton
	};
	private HashMap<JButton, String> appendingText = new HashMap<>();

	public Gui(JFrame frame){
		setUpButtons();
		setAppendingText();
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
	}

	public void attachObserver(Observer observer) {
		reqObserver = observer;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearButton){
			mainTextArea.setText("");
		}
		if (e.getSource() == equalsButton){
			String userInput = mainTextArea.getText().strip();
			Request request = new Request(userInput);
			respond(request);
		}
		if (appendingText.containsKey(e.getSource())){
			mainTextArea.append(appendingText.get(e.getSource()));
		}
		if (e.getSource() == percentageButton){
			mainTextArea.append("%");
			equalsButton.doClick();
			mainTextArea.append("%");
		}
		if (e.getSource() == exponentButton){
			mainTextArea.append("^");
		}
	}
	private void setUpButtons(){
		for (JButton operationButton : operationButtons) {
			operationButton.addActionListener(this);
		}
		for (JButton numericalButton : numericalButtons){
			numericalButton.addActionListener(this);
		}
	}
	private void setAppendingText(){
		JButton[] appendingButtons = {
				addButton, subtractButton, multiplyButton, divideButton,
				decimalButton, rightParenthesisButton, leftParenthesisButton,
				a0Button, a1Button, a2Button, a3Button, a4Button, a5Button,
						a6Button, a7Button, a8Button, a9Button
		};
		for (int i = 0; i < appendingButtons.length; i++){
			appendingText.put(appendingButtons[i], appendingButtons[i].getText());
		}
	}
	private void respond(Request request){
		mainTextArea.setText(reqObserver.update(request));
	}



}

