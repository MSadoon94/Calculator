package org.calculator.user;

import javax.swing.*;
import java.awt.*;

class AppenderPanel extends Panel {
	private JPanel numeralPanel, operationPanel, mixedPanel;

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
		operationPanel = new JPanel(new GridLayout(4, 2, 2, 1));
		mixedPanel = new JPanel(new GridLayout(1, 2, 2, 1));
	}

	private void setButtons(){
		String[] operations = {"(", ")", "^", "√", "+", "-", "*", "/",};
		String[] mixed = {"0", "."};
		setSymbolButtons(operationPanel, operations);
		setNumericalButtons();
		setSymbolButtons(mixedPanel, mixed);

	}

	private void setNumericalButtons(){
		for (int i = 9; i >= 1; i--){
			JButton button = new JButton(String.valueOf(i));
			button.setFont(new Font("TimesRoman", Font.BOLD, 14));
			numeralPanel.add(button);
			setActionListener(button);
		}
	}

	private void setSymbolButtons(JPanel panel, String[] symbols){
		for (String value : symbols) {
			JButton button = new JButton(value);
			button.setFont(new Font("TimesRoman", Font.BOLD, 14));
			panel.add(button);
			setActionListener(button);
		}
	}

	private void addComponents(){
		add(numeralPanel, BorderLayout.CENTER);
		add(mixedPanel, BorderLayout.SOUTH);
		add(operationPanel, BorderLayout.EAST);
	}

	private void setActionListener(JButton button){
		button.addActionListener(e -> appendTextArea(button));
	}

	private void appendTextArea(JButton button){
		textArea.append(button.getText());
	}

}
