package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

import javax.swing.*;


public class Gui implements UiActions {

	private UserCache inputCache;
	private JPanel mainPanel;
	private HistoryPanel inputHistoryPanel, answerHistoryPanel;
	private TextAppendingPanel textAppendingPanel;
	private TextFunctionPanel textFunctionPanel;
	private JTextArea textArea;
	private JFrame frame;
	private int decimalPosition = 2;

	public Gui(JFrame frame){
		this.frame = frame;
		setTextArea();
		mainPanel = new JPanel();
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
	}

	private void setTextArea() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
	}

	public void addInputCache(UserCache userCache){
		this.inputCache = userCache;
	}

	public void doInputCacheActions(Request request){
		inputCache.addRequest(request);
		inputHistoryPanel.setTextToMostRecentInput();
	}

	public void addInputHistoryPanel(HistoryPanel panel){
		inputHistoryPanel = panel;
		mainPanel.add(inputHistoryPanel);
	}

	public void addAnswerHistoryPanel(HistoryPanel panel){
		answerHistoryPanel = panel;
		mainPanel.add(panel);
	}
	public JTextArea textArea(){
		return textArea;
	}
	public void inputErrorMessage(String invalidInput){
		JOptionPane.showMessageDialog(
				frame,
				"Cannot compute user request: " + "\"" + invalidInput + "\"",
				"User Input Error",
				JOptionPane.ERROR_MESSAGE
		);
	}

	public void decimalPositionError(){
		JOptionPane.showMessageDialog(
				frame,
				"Decimal position can only be whole number.",
				"Decimal Position Error",
				JOptionPane.ERROR_MESSAGE
		);
	}

	public void addTextAppendingPanel(TextAppendingPanel panel){
		panel.attachTextArea(textArea);
		textAppendingPanel = panel;
		mainPanel.add(textAppendingPanel);
	}

	public void addTextFunctionPanel(TextFunctionPanel panel){
		textFunctionPanel = panel;
		mainPanel.add(textFunctionPanel);
	}
}

