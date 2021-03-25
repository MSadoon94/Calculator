package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.processing.Invoker;
import org.calculator.verification.Verifier;

import javax.swing.*;
import java.awt.*;

class FunctionsPanel extends Panel{
	private Verifier errorVerifier;
	private Observer observer;
	private Invoker answerInvoker;
	private JButton decimalPositionButton, notationButton,
			 percentageButton, equalsButton;
	private JButton[] buttons;
	private int position = 2;

	public FunctionsPanel(Verifier aErrorVerifier, Invoker invoker, Observer observer){
		errorVerifier = aErrorVerifier;
		this.observer = observer;
		answerInvoker = invoker;
		createPanel();
	}

	private void createPanel(){
		setName("Functions");
		setButtons();
		setButtonArray();
		setLayout(new GridLayout(4, 1));
		addComponents();
		setActionListener();
	}

	private void setButtons(){
		decimalPositionButton = new JButton("Decimal Position");
		notationButton = new JButton("Notation");
		percentageButton = new JButton("%");
		equalsButton = new JButton("=");


		decimalPositionButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
		notationButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
		percentageButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
		equalsButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
	}

	private void setButtonArray(){
		buttons = new JButton[]{
				decimalPositionButton, notationButton,
				percentageButton, equalsButton
				};
	}

	private void addComponents(){
		for (JButton button : buttons) {
			add(button);
		}
	}

	private void setActionListener(){
		decimalPositionButton.addActionListener(e -> decimal());
		notationButton.addActionListener(e -> notation());
		percentageButton.addActionListener(e -> percentage());
		equalsButton.addActionListener(e -> calculate());
	}

	private void decimal(){
		String decimalInput = textArea.getText().strip();
		position = errorVerifier.checkDecimalInput(decimalInput);
		textArea.setText("");
	}

	private void notation(){
		Request notation = calculatedRequest(request());
		textArea.setText(notation.scientificNotation());
	}

	private void percentage(){
		textArea.append("%");
		equalsButton.doClick();
		textArea.append("%");
	}

	private void calculate(){
		Request request = request();
		if(!request.input().isBlank()) {
			Request result = calculatedRequest(request);
			observer.update(request, result);
		}
	}

	private Request request(){
		Request request = errorVerifier.checkedInput(textArea.getText().trim());
		request.setDecimalPosition(position);
		return request;
	}

	private Request calculatedRequest(Request request){
		Request result;

		if(!request.input().equals("Invalid Input")){
			result = answerInvoker.answer(request);
			textArea.setText(result.input());
		} else {
			result = request;
		}

		return result;
	}
}
