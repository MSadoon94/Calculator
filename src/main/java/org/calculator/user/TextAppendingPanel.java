package org.calculator.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

class TextAppendingPanel extends Panel {
	private JTextArea textArea;
	private ButtonGroup buttons = new ButtonGroup();
	private JButton[] numericalButtons = new JButton[10];
	private JButton[] symbolButtons;

	public TextAppendingPanel(){
		super();
		createPanel();
	}

	public ActionSet actions() {
		ActionSet set = new ActionSet();
		buttons.getElements().asIterator().forEachRemaining(button -> {
			ActionEvent event =
					new ActionEvent(button, ActionEvent.ACTION_FIRST, button.getText());
			set.addConsumerAction(event, appendTextArea(button));
				});
		return set;
	}

	private void createPanel(){
		setSymbolButtons();
		setNumericalButtons();
		this.setLayout(new GridLayout(0,4));
		panelWithComponentsAdded(this);
		setActionListener(this);
	}

	private void setNumericalButtons(){
		for (int i = 0; i < numericalButtons.length; i++){
			numericalButtons[i] = new JButton(String.valueOf(i));
			buttons.add(numericalButtons[i]);
		}
	}

	private void setSymbolButtons(){
		String[] symbols = {"+", "-", "*", "/", ".", "(", ")", "^", "√"};
		symbolButtons = new JButton[symbols.length];
		for (int i = 0; i < symbolButtons.length; i++){
			symbolButtons[i] = new JButton(symbols[i]);
			buttons.add(symbolButtons[i]);
		}
	}

	private JPanel panelWithComponentsAdded(JPanel panel){
		buttons.getElements().asIterator().forEachRemaining(
				button -> panel.add(button)
		);
		return panel;
	}

	private void setActionListener(Panel panel){
		ButtonListener listener = new ButtonListener(panel);
		buttons.getElements()
				.asIterator()
				.forEachRemaining(button -> button.addActionListener(listener));
		buttons.getElements()
				.asIterator()
				.forEachRemaining(button -> button.addActionListener(e -> appendTextArea2(button)));
	}

	private void appendTextArea2(AbstractButton button){
		textArea.append(button.getText());
	}

	private Consumer<Object> appendTextArea(AbstractButton button){
		return consumer -> {
			textArea.append(button.getText());
		};
	}
}
