package org.calculator.user;

import org.calculator.common.Request;

import javax.swing.*;
import java.awt.*;

class HistoryPanel extends AccessoryPanel{

	private JTextField textField;
	private JButton back, next;
	private JLabel label;
	private UserCache cache;

	public HistoryPanel(JLabel aLabel, UserCache aCache){
		super();
		label = aLabel;
		setName(label.getText());
		cache = aCache;
		createPanel();
	}

	public void addRequest(Request request){
		cache.addRequest(request);
		setTextFieldToPreviousEntry();
	}

	private void createPanel(){
		setTextField();
		setLabel();
		setButtons();
		BorderLayout layout = new BorderLayout(2, 1);
		setLayout(layout);
		addComponents();
		setActionListener();
	}

	private void setTextField(){
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(Color.white);
		textField.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	}
	private void setButtons(){
		back = new JButton("<");
		back.setName("Back");
		next = new JButton(">");
		next.setName("Next");
	}

	private void setLabel(){
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("TimesRoman", Font.BOLD, 16));
	}

	private void addComponents(){
		add(label, BorderLayout.NORTH);
		add(textField, BorderLayout.CENTER);
		add(back, BorderLayout.WEST);
		add(next, BorderLayout.EAST);
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
