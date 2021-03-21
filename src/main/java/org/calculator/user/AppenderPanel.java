package org.calculator.user;

import javax.swing.*;
import java.awt.*;

class AppenderPanel extends Panel {
	private JPanel numeralPanel, symbolPanel, mixedPanel;
	private ButtonGroup buttons = new ButtonGroup();
	private JButton[] numericalButtons = new JButton[10];
	private JButton[] symbolButtons, mixedButtons;

	public AppenderPanel(){
		textArea.setText("test");
		createPanel();
	}

	private void createPanel(){
		setSubPanels();
		setLayout(new BorderLayout());
		setButtons();
		addComponents();
		setActionListener();

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
		buttons.getElements()
				.asIterator()
				.forEachRemaining(button ->
						button.setFont(new Font("TimesRoman", Font.BOLD, 14)));
	}

	private void setNumericalButtons(){
		for (int i = 9; i >= 1; i--){
			numericalButtons[i] = new JButton(String.valueOf(i));
			buttons.add(numericalButtons[i]);
			numeralPanel.add(numericalButtons[i]);
		}
	}

	private void setSymbolButtons(){
		String[] symbols = {"(", ")", "^", "√", "+", "-", "*", "/",};
		symbolButtons = new JButton[symbols.length];
		for (int i = 0; i < symbolButtons.length; i++){
			symbolButtons[i] = new JButton(symbols[i]);
			buttons.add(symbolButtons[i]);
			symbolPanel.add(symbolButtons[i]);
		}
	}

	private void setMixedButtons(){
		String[] mixed = {"0", "."};
		mixedButtons = new JButton[mixed.length];
		for(int i = 0; i < mixed.length; i++){
			mixedButtons[i] = new JButton(mixed[i]);
			buttons.add(mixedButtons[i]);
			mixedPanel.add(mixedButtons[i]);
		}
	}

	private void addComponents(){
		add(numeralPanel, BorderLayout.CENTER);
		add(mixedPanel, BorderLayout.SOUTH);
		add(symbolPanel, BorderLayout.EAST);

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
