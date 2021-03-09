package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Gui implements ActionListener, UiActions {

	private Observer reqObserver;
	private UserCache inputCache;
	private JPanel mainPanel;
	private JTextArea textArea;
	private JButton equalsButton, addButton, subtractButton, divideButton,
			multiplyButton, percentageButton, clearButton, decimalButton,
			rightParenthesisButton, leftParenthesisButton, exponentButton,
			a0Button, a1Button, a2Button, a3Button, a4Button, a5Button,
			a6Button, a7Button, a8Button, a9Button, squareRootButton,
			decimalPositionButton, inputBackButton, inputNextButton,
			answerBackButton, answerNextButton;
	private JTextField answerHistoryTextField, inputHistoryTextField;
	private JLabel inputHistoryLabel, lastAnswerLabel;
	private JButton[] numericalButtons = {
			a0Button, a1Button, a2Button, a3Button, a4Button,
			a5Button, a6Button, a7Button, a8Button, a9Button,
			decimalButton
	};
	private JButton[] operationButtons = {
			equalsButton, addButton, subtractButton, divideButton,
			multiplyButton, percentageButton, clearButton,
			rightParenthesisButton, leftParenthesisButton, exponentButton,
			squareRootButton, decimalPositionButton, inputBackButton, inputNextButton,
			answerBackButton, answerNextButton
	};
	private HashMap<JButton, String> appendingText = new HashMap<>();

	private JFrame frame;
	private int decimalPosition = 2;

	public Gui(JFrame frame){
		setUpButtons();
		setAppendingText();
		textArea.setLineWrap(true);
		this.frame = frame;
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
	}

	public void attachObserver(Observer observer) {
		reqObserver = observer;
	}

	public void addInputCache(UserCache userCache){
		this.inputCache = userCache;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearButton){
			textArea.setText("");
		}
		if (e.getSource() == equalsButton){
			Request request = new Request(validatedInput());
			request.setDecimalPosition(decimalPosition);
			doInputCacheActions(request);
			respond(request);
		}
		if (appendingText.containsKey(e.getSource())){
			textArea.append(appendingText.get(e.getSource()));
		}
		if (e.getSource() == percentageButton){
			textArea.append("%");
			equalsButton.doClick();
			textArea.append("%");
		}
		if (e.getSource() == exponentButton){
			textArea.append("^");
		}
		if (e.getSource() == decimalPositionButton){
			changeDecimalPosition(textArea.getText().strip());
			clearButton.doClick();
		}
		if (e.getSource() == inputNextButton){
			if (inputCache.hasNext()){
				inputHistoryTextField.setText(inputCache.next().input());
			}

		}
		if (e.getSource() == inputBackButton){
			if(inputCache.hasPrevious()){
				inputHistoryTextField.setText(inputCache.previous().input());
			}
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
				a6Button, a7Button, a8Button, a9Button, squareRootButton
		};
		for (int i = 0; i < appendingButtons.length; i++){
			appendingText.put(appendingButtons[i], appendingButtons[i].getText());
		}
	}

	private void doInputCacheActions(Request request){
		inputCache.addRequest(request);
		inputHistoryTextField.setText(inputCache.previous().input());
	}

	private void respond(Request request){
		if(!request.input().contains("Input Error")) {
			textArea.setText(reqObserver.update(request));
		}
	}
	private String validatedInput(){
		String validated = textArea.getText().strip();
		InputValidator validator = new InputValidator();
		if(!validator.isValidInput(textArea.getText())){
			JOptionPane.showMessageDialog
				(
						frame,
						"Cannot compute user request: " + "\"" + validator.invalidInput() + "\"",
						"User Input Error",
						JOptionPane.ERROR_MESSAGE
				);
			validated = "Input Error";
		}
		return validated;
	}

	private void changeDecimalPosition(String position){
		InputValidator validator = new InputValidator();
		if(!validator.isValidDecimalPosition(position)){
			JOptionPane.showMessageDialog
			(
					frame,
					"Decimal position can only be whole number.",
					"Decimal Position Error",
					JOptionPane.ERROR_MESSAGE
			);
		} else {
			decimalPosition = Integer.parseInt(position);
		}

	}



}

