package org.calculator.user;

import javax.swing.*;
import java.awt.*;

public class EraserPanel extends Panel {
	private JButton clearButton, backspaceButton;
	
	public EraserPanel() {
		createPanel();
	}

	private void createPanel(){
		setButtons();
		setLayout(new GridLayout(0, 1));
		addComponents();
		setActionListener();
	}
	private void setButtons(){
		clearButton = new JButton("C");
		backspaceButton = new JButton("←");

		clearButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
		backspaceButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
	}
	
	private void addComponents(){
		add(clearButton);
		add(backspaceButton);
	}
	
	private void setActionListener(){
		clearButton.addActionListener(e -> clearAll());
		backspaceButton.addActionListener(e -> backspace());
	}
	
	private void clearAll(){
		textArea.setText("");
	}

	private void backspace(){
		String text = textArea.getText();
		textArea.setText(text.substring(0, text.length() - 1));
	}
}
