package org.calculator.user;

import javax.swing.*;
import java.awt.*;

class AppenderPanel extends Panel {
	private JPanel numeralPanel, symbolPanel, mixedPanel;

	public AppenderPanel(){
		createPanel();
	}

	private void createPanel(){
		setSubPanels();
		setLayout(new BorderLayout());
		setButtons();
		addComponents();
	}

	private void setSubPanels(){
		numeralPanel = new JPanel(new GridLayout(3, 4, 2, 1));
		symbolPanel = new JPanel(new GridLayout(4, 2, 2, 1));
		mixedPanel = new JPanel(new GridLayout(1, 2, 2, 1));
	}

	private void setButtons(){
		setSymbolButtons();
		setNumericalButtons();
		setMixedButtons();

	}

	private void setNumericalButtons(){
		for (int i = 9; i >= 1; i--){
			JButton button = new JButton(String.valueOf(i));
			numeralPanel.add(button);
			setActionListener(button);
		}
	}

	private void setSymbolButtons(){
		String[] symbols = {"(", ")", "^", "√", "+", "-", "*", "/",};
		for (String symbol : symbols) {
			JButton button = new JButton(symbol);
			symbolPanel.add(button);
			setActionListener(button);
		}
	}

	private void setMixedButtons(){
		String[] mixed = {"0", "."};
		for (String value : mixed) {
			JButton button = new JButton(value);
			mixedPanel.add(button);
			setActionListener(button);
		}
	}

	private void addComponents(){
		add(numeralPanel, BorderLayout.CENTER);
		add(mixedPanel, BorderLayout.SOUTH);
		add(symbolPanel, BorderLayout.EAST);
	}

	private void setActionListener(JButton button){
		button.addActionListener(e -> appendTextArea(button));
	}

	private void appendTextArea(JButton button){
		textArea.append(button.getText());
	}

}
