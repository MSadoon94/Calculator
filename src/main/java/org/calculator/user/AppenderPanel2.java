package org.calculator.user;

import javax.swing.*;
import java.awt.*;


class AppenderPanel2 extends Panel2 {
	private JTextArea textArea;
	private ButtonGroup buttons = new ButtonGroup();
	private JButton[] numericalButtons = new JButton[10];
	private JButton[] symbolButtons;

	public AppenderPanel2(Ui gui){
		super();
		textArea = gui.textArea();
		createPanel();
	}

	protected Panel2 panel() {
		return this;
	}

	private void createPanel(){
		setSymbolButtons();
		setNumericalButtons();
		this.setLayout(new GridLayout(0,4));
		addComponents();
		setActionListener();
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

	private void addComponents(){
		buttons.getElements().asIterator().forEachRemaining(
				this::add
		);
	}

	private void setActionListener(){
		buttons.getElements()
				.asIterator()
				.forEachRemaining(button -> button.addActionListener(e -> appendTextArea(button)));
	}

	private void appendTextArea(AbstractButton button){
		textArea.append(button.getText());
	}


}
