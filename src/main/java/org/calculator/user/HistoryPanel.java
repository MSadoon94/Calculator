package org.calculator.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.function.Consumer;

class HistoryPanel extends JPanel implements Panel {

	private HistoryPanel thisPanel;
	private JTextField textField;
	private JButton back, next;
	private JButton[] buttons;
	private JLabel label;
	private UserCache cache;

	public HistoryPanel(JLabel label, UserCache cache){
		super();
		thisPanel = this;
		this.label = label;
		this.cache = cache;
	}

	public JPanel getPanel() {
		textField = new JTextField();
		setButtons();
		thisPanel.setLayout(new BorderLayout());
		panelWithComponentsAdded(thisPanel);
		setActionListener(thisPanel);
		return thisPanel;
	}

	public JTextField textField() {
		return textField;
	}

	public JButton button(String name) {
		JButton result = null;
		for (JButton button : buttons) {
			if (button.getName().equals(name)){
				result = button;
			}
		}
		return result;
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
	}
	private JPanel panelWithComponentsAdded(JPanel panel){
		panel.add(label, BorderLayout.NORTH);
		panel.add(textField, BorderLayout.CENTER);
		panel.add(back, BorderLayout.WEST);
		panel.add(next, BorderLayout.EAST);
		return panel;
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
