package org.calculator.user;

import javax.swing.*;
import java.awt.*;

public class EraserPanel extends AppenderPanel2{
	private JButton clearButton;
	protected JTextArea textArea;
	
	public EraserPanel() {
		super();
		this.textArea = super.textArea;
		createPanel();
		super.panel().add(this);
	}

	private void createPanel(){
		setButtons();
		this.setLayout(new GridLayout(0, 0));
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
