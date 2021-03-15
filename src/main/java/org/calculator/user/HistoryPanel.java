package org.calculator.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

class HistoryPanel extends Panel {

	private JTextField textField;
	private JButton back, next;
	private JButton[] buttons;
	private JLabel label;
	private UserCache cache;

	public HistoryPanel(JLabel label, UserCache cache){
		super();
		this.label = label;
		this.cache = cache;
		createPanel();
	}

	public ActionSet actions(){

		ActionSet set = new ActionSet();
		ActionEvent backAction =
				new ActionEvent(back,ActionEvent.ACTION_FIRST, "PreviousEntry");
		ActionEvent nextAction =
				new ActionEvent(next,ActionEvent.ACTION_FIRST, "NextEntry");
		set.addConsumerAction(backAction, setTextFieldToPreviousEntry(cache) );
		set.addConsumerAction(nextAction, setTextFieldToNextEntry(cache));
		return set;
	}

	private void createPanel(){
		textField = new JTextField();
		setButtons();
		this.setLayout(new BorderLayout());
		panelWithComponentsAdded(this);
		setActionListener(this);
	}

	private void setButtons(){
		back = new JButton("<");
		back.setName("Back");
		next = new JButton(">");
		next.setName("Next");
		buttons = new JButton[]{back, next};
	}

	private void setActionListener(Panel panel){
		ButtonListener listener = new ButtonListener(panel);
		for (JButton button : buttons) {
			button.addActionListener(listener);
		}
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

	private JPanel panelWithComponentsAdded(JPanel panel){
		panel.add(label, BorderLayout.NORTH);
		panel.add(textField, BorderLayout.CENTER);
		panel.add(back, BorderLayout.WEST);
		panel.add(next, BorderLayout.EAST);
		return panel;
	}


	private Consumer<Object> setTextFieldToPreviousEntry(UserCache cache){
		return consumer -> {
			if (cache.hasPrevious()) {
				textField.setText(cache.previous().input());
			}
		};
	}

	private Consumer<Object> setTextFieldToNextEntry(UserCache cache){
		return consumer -> {
			if (cache.hasNext()) {
				textField.setText(cache.next().input());
			}
		};
	}
}
