package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui implements ActionListener, UiActions {

	private Observer reqObserver;
	private JPanel mainPanel;
	private JTextArea mainTextArea;
	private JButton equalsButton, addButton, subtractButton, divideButton, multiplyButton, percentageButton;

	public Gui(JFrame frame){
		equalsButton.addActionListener(this);
		addButton.addActionListener(this);
		subtractButton.addActionListener(this);
		divideButton.addActionListener(this);
		multiplyButton.addActionListener(this);
		percentageButton.addActionListener(this);
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);

	}

	public void attachObserver(Observer observer) {
		reqObserver = observer;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == equalsButton){
			String userInput = mainTextArea.getText().strip();
			System.out.println(userInput);
			Request request = new Request(userInput);
			respond(request);
		}
		if (e.getSource() == addButton){
			mainTextArea.append("+");
		}
		if (e.getSource() == subtractButton){
			mainTextArea.append("-");
		}
		if (e.getSource() == multiplyButton){
			mainTextArea.append("*");
		}
		if (e.getSource() == divideButton){
			mainTextArea.append("/");
		}
		if (e.getSource() == percentageButton){
			mainTextArea.append("%");
			equalsButton.doClick();
			mainTextArea.append("%");
		}
	}

	private void respond(Request request){
		mainTextArea.setText(reqObserver.update(request));
	}



}

