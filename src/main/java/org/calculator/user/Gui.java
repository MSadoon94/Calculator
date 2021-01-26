package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;
import org.calculator.request.RequestServices;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui implements ActionListener, UiActions {

	private RequestServices reqServices;
	private Observer reqObserver;
	private JPanel mainPanel;
	private JTextArea mainTextArea;
	private JButton equalsButton;
	private JButton addButton;
	private JButton subtractButton;
	private JButton divideButton;
	private JButton multiplyButton;
	private JButton percentageButton;


	public Gui(JFrame frame, RequestServices services){
		reqServices = services;
		equalsButton.addActionListener(this);
		addButton.addActionListener(this);
		subtractButton.addActionListener(this);
		divideButton.addActionListener(this);
		multiplyButton.addActionListener(this);
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);

	}

	public void attachObserver(Observer observer) {
		reqObserver = observer;
	}

	public Request getRequest() {
		return reqServices.getRequest();
	}

	public void setResponse(String response) {
		mainTextArea.setText(response);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == equalsButton){
			addRequest();
			sendRequest(mainTextArea.getText().trim());
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
	}

	private void addRequest() {
		reqServices.addRequest(mainTextArea.getText().trim());
	}

	private void sendRequest(String in){
		reqObserver.update(in);
	}



}

