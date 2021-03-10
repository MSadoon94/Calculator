package org.calculator.user;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class HistoryPanel implements Panel {

	private JTextField textField;
	private JButton back, next;
	private JLabel label;

	public HistoryPanel(JLabel label){
		this.label = label;
	}
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		textField = new JTextField();
		back = new JButton("Back");
		next = new JButton("Next");
		panel.setLayout(new BorderLayout());
		return panelWithComponentsAdded(panel);
	}

	public JTextField textField() {
		return textField;
	}

	public JButton button(String name) {
		JButton[] buttons = {back, next};
		JButton result = null;
		for (JButton button : buttons) {
			if (button.getText().equals(name)){
				result = button;
			}
		}
		return result;
	}

	private JPanel panelWithComponentsAdded(JPanel panel){
		panel.add(label, BorderLayout.NORTH);
		panel.add(textField, BorderLayout.CENTER);
		panel.add(back, BorderLayout.CENTER);
		panel.add(next, BorderLayout.CENTER);
		return panel;
	}
}
