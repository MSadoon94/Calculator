package org.calculator.user;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class Gui implements Ui {

	private JPanel mainPanel;
	private JFrame frame;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private HashMap<String, Panel> panels = new HashMap<>();

	public Gui(JFrame frame){
		this.frame = frame;
		setLayout();
		mainPanel = new JPanel(layout);
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);

	}

	public void setPanels(Panel panel){
		panels.put(panel.getName(), panel);
	}

	public void addPanelsToMainPanel(){
		addComponent(panels.get("Input History"), 0,0,1,1);
		addComponent(panels.get("Answer History"), 1,0,1,1);
		addComponent(panels.get("Functions"), 0,2,3,4);
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

	private void addComponent(Component component, int gridX, int gridY, int gridWidth, int gridHeight){
		gbc.weightx = 1;
		gbc.weighty = 1;

		gbc.gridx = gridX;
		gbc.gridy = gridY;

		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;

		layout.setConstraints(component, gbc);
		mainPanel.add(component);
	}
}

