package org.calculator.user;

import org.calculator.common.Request;

import javax.swing.*;
import java.awt.*;

class HistoryPanel extends AccessoryPanel{

	private JTextField textField;
	private JButton back, next;
	private JLabel label;
	private UserCache cache;

	public HistoryPanel(JLabel label, UserCache cache){
		super();
		this.label = label;
		this.cache = cache;
		createPanel();
	}

	public void addRequest(Request request){
		cache.addRequest(request);
		setTextFieldToPreviousEntry();
	}

	private void createPanel(){
		textField = new JTextField();
		setButtons();
		this.setLayout(new BorderLayout());
		addComponents();
		setActionListener();
	}

	private void setButtons(){
		back = new JButton("<");
		back.setName("Back");
		next = new JButton(">");
		next.setName("Next");
	}

	private void addComponents(){
		this.add(label, BorderLayout.NORTH);
		this.add(textField, BorderLayout.CENTER);
		this.add(back, BorderLayout.WEST);
		this.add(next, BorderLayout.EAST);
	}

	private void setActionListener(){
		back.addActionListener(e -> setTextFieldToPreviousEntry());
		next.addActionListener(e -> setTextFieldToNextEntry());
	}

	private void setTextFieldToPreviousEntry(){
		if (cache.hasPrevious()) {
			textField.setText(cache.previous().input());
		}
	}

	private void setTextFieldToNextEntry(){
		if (cache.hasNext()) {
			textField.setText(cache.next().input());
		}
	}


}
