package org.calculator.user;

import javax.swing.*;
import java.awt.*;


class AppenderPanel extends Panel {
	private ButtonGroup buttons = new ButtonGroup();
	private JButton[] numericalButtons = new JButton[10];
	private JButton[] symbolButtons;

	public AppenderPanel(){
		textArea.setText("test");
		createPanel();
	}

	private void createPanel(){
		setLayout(new GridLayout(0,2));
		setSymbolButtons();
		setNumericalButtons();
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
