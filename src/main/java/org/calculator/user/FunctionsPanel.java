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
	private JButton equalsButton,
			percentageButton, decimalPositionButton;
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
		setLayout(new GridLayout(3, 3));
		addComponents();
		setActionListener();
	}

	private void setButtons(){
		equalsButton = new JButton("=");
		percentageButton = new JButton("%");
		decimalPositionButton = new JButton("Decimal Position");
	}

	private void setButtonArray(){
		buttons = new JButton[]{
				decimalPositionButton,
				percentageButton, equalsButton
				};
	}

	private void addComponents(){
		for (JButton button : buttons) {
			add(button);
		}
	}

	private void setActionListener(){
		equalsButton.addActionListener(e -> calculate());
		percentageButton.addActionListener(e -> percentage());
		decimalPositionButton.addActionListener(e -> decimal());
	}



	private void calculate(){
			Request request = request();
			Request result = calculatedRequest();
			observer.update(request, result);
	}

	private void percentage(){
			textArea.append("%");
			equalsButton.doClick();
			textArea.append("%");
	}

	private void decimal(){
		String decimalInput = textArea.getText().strip();
		position = errorVerifier.checkDecimalInput(decimalInput);
		textArea.setText("");
	}

	private Request request(){
		String validated = textArea.getText().strip();
		Request request = new Request(validated);
		request.setDecimalPosition(position);
		return request;
	}

	private Request calculatedRequest(){
		Request request = errorVerifier.checkedInput(textArea.getText().trim());
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
