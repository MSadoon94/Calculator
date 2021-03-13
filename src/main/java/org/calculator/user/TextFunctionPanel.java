package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class TextFunctionPanel extends JPanel implements Panel{
	private TextFunctionPanel thisPanel;
	private Observer observer;
	private Gui gui;
	private ActionEvent clear, calculate, percentage, decimalPosition;
	private JTextArea textArea;
	private JButton clearButton, equalsButton,
			percentageButton, decimalPositionButton;
	private JButton[] buttons;
	private int position = 2;

	public TextFunctionPanel(Gui gui, Observer observer){
		super();
		thisPanel = this;
		this.gui = gui;
		this.textArea = gui.textArea();
		this.observer = observer;
		createPanel();
	}

	public ActionSet actions() {
		ActionSet set = new ActionSet();
		set.addConsumerAction(clear, clearAll());
		set.addConsumerAction(calculate, calculate());
		set.addConsumerAction(percentage, percentage());
		set.addConsumerAction(decimalPosition, decimal());
		return set;
	}


	private void createActionEvents(){
		clear = new ActionEvent(clearButton, ActionEvent.ACTION_FIRST, "Clear");
		calculate = new ActionEvent(equalsButton, ActionEvent.ACTION_FIRST, "Calculate");
		percentage = new ActionEvent(percentageButton, ActionEvent.ACTION_FIRST, "Percentage");
		decimalPosition = new ActionEvent(decimalPositionButton, ActionEvent.ACTION_FIRST, "DecimalPosition");
	}

	private void createPanel(){
		setButtons();
		setButtonArray();
		thisPanel.setLayout(new BorderLayout());
		panelWithComponentsAdded(thisPanel);
		setActionListener(thisPanel);
		createActionEvents();
	}

	private void setButtons(){
		clearButton = new JButton("C");
		equalsButton = new JButton("=");
		percentageButton = new JButton("%");
		decimalPositionButton = new JButton("Decimal Position");
	}

	private void setButtonArray(){
		buttons = new JButton[]{
				clearButton, equalsButton,
				percentageButton, decimalPositionButton
				};
	}

	private JPanel panelWithComponentsAdded(JPanel panel){
		for (JButton button : buttons) {
			panel.add(button, BorderLayout.CENTER);
		}
		return panel;
	}

	private void setActionListener(Panel panel){
		ButtonListener listener = new ButtonListener(panel);
		for (JButton button : buttons) {
			button.addActionListener(listener);
		}
	}

	private Consumer<Object> clearAll(){
		return consumer -> textArea.setText("");
	}

	private Consumer<Object> calculate(){
		return consumer -> {
			Request request = request();
			gui.doInputCacheActions(request);
			validateRequest(request);
		};
	}

	private Consumer<Object> percentage(){
		return consumer -> {
			textArea.append("%");
			equalsButton.doClick();
			textArea.append("%");
		};
	}

	private Consumer<Object> decimal(){
		return consumer -> {
			InputValidator validator = new InputValidator();
			String decimalInput = textArea.getText().strip();
			if (!validator.isValidDecimalPosition(decimalInput)) {
				gui.decimalPositionError();
			} else {
				position = Integer.parseInt(decimalInput);
				clearButton.doClick();
			}
		};
	}

	private Request request(){
		String validated = textArea.getText().strip();
		Request request = new Request(validated);
		request.setDecimalPosition(position);
		return request;
	}

	private void validateRequest(Request request){
		InputValidator validator = new InputValidator();
		if(!validator.isValidInput(textArea.getText())){
			gui.inputErrorMessage(validator.invalidInput());
		} else {
			textArea.setText(observer.update(request));
		}
	}
}
