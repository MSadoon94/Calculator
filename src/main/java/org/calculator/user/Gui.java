package org.calculator.user;

import javax.swing.*;
import java.awt.*;

class Gui implements Ui {

	private JPanel mainPanel;
	private JFrame frame;
	private GridBagLayout layout;
	private GridBagConstraints gbc;

	public Gui(JFrame frame){
		this.frame = frame;
		setLayout();
		mainPanel = new JPanel(layout);
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);

	}

	public void addPanels(Panel inputHistory, Panel answerHistory, CompositePanel entryPanel){
		addComponent(inputHistory, 1, 0, 0,0,1,1);
		addComponent(answerHistory, 1, 0, 1,0,1,1);
		addComponent(entryPanel.panel(),2, 2, 0,1,2,2);
	}

	public void inputErrorMessage(String invalidInput){
		JOptionPane.showMessageDialog(
				frame,
				"Cannot compute user request: " + "\"" + invalidInput + "\"",
				"User Input Error",
				JOptionPane.ERROR_MESSAGE
		);
	}

	public void decimalPositionError(){
		JOptionPane.showMessageDialog(
				frame,
				"Decimal position can only be whole number.",
				"Decimal Position Error",
				JOptionPane.ERROR_MESSAGE
		);
	}
	
	private void setLayout(){
		layout =  new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
	}

	private void addComponent(Component component, int weightX, int weightY, int gridX, int gridY, int gridWidth, int gridHeight){
		gbc.weightx = weightX;
		gbc.weighty = weightY;

		gbc.gridx = gridX;
		gbc.gridy = gridY;

		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;

		layout.setConstraints(component, gbc);
		mainPanel.add(component);
	}
}

