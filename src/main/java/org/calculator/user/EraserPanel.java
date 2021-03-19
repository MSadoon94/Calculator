package org.calculator.user;

import javax.swing.*;
import java.awt.*;

public class EraserPanel extends AppenderPanel2{
	private JButton clearButton;
	
	public EraserPanel() {
		super();
		createPanel();
		setPanel();
	}

	private void createPanel(){
		setButtons();
		this.setLayout(new GridLayout(0, 1));
		addComponents();
		setActionListener();
	}
	private void setButtons(){
		clearButton = new JButton("C");
	}
	
	private void addComponents(){
		this.add(clearButton);
	}
	
	private void setActionListener(){
		clearButton.addActionListener(e -> clearAll());
	}
	
	private void clearAll(){
		textArea.setText("");
	}
}
