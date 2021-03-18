package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.processing.Invoker;

import javax.swing.*;
import java.awt.*;

class FunctionsPanel2 extends EraserPanel{
	private Observer observer;
	private Ui gui;
	private Invoker answerInvoker;
	protected JTextArea textArea;
	private JButton equalsButton,
			percentageButton, decimalPositionButton;
	private JButton[] buttons;
	private int position = 2;

	public FunctionsPanel2(Ui gui, Invoker invoker, Observer observer){
		super();
		this.gui = gui;
		this.textArea = super.textArea;
		this.observer = observer;
		answerInvoker = invoker;
		createPanel();
		super.panel().add(this);
	}

	private void createPanel(){
		setButtons();
		setButtonArray();
		this.setLayout(new GridLayout(0, 2));
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
			this.add(button);
		}
	}

	private void setActionListener(){
		equalsButton.addActionListener(e -> calculate());
		percentageButton.addActionListener(e -> percentage());
		decimalPositionButton.addActionListener(e -> decimal());
	}



	private void calculate(){
			Request request = request();
			Request result = validatedRequest(request);
			observer.update(request, result);
	}

	private void percentage(){
			textArea.append("%");
			equalsButton.doClick();
			textArea.append("%");
	}

	private void decimal(){
			InputValidator validator = new InputValidator();
			String decimalInput = textArea.getText().strip();
			if (!validator.isValidDecimalPosition(decimalInput)) {
				gui.decimalPositionError();
			} else {
				position = Integer.parseInt(decimalInput);
				textArea.setText("");
			}
	}

	private Request request(){
		String validated = textArea.getText().strip();
		Request request = new Request(validated);
		request.setDecimalPosition(position);
		return request;
	}

	private Request validatedRequest(Request request){
		InputValidator validator = new InputValidator();
		Request result;
		if(!validator.isValidInput(textArea.getText())){
			gui.inputErrorMessage(validator.invalidInput());
			result = new Request("Invalid Input");
		} else {
			result = answerInvoker.answer(request);
			textArea.setText(result.input());
		}
		return result;
	}
}
