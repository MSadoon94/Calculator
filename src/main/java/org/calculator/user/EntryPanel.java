package org.calculator.user;

import org.calculator.processing.Invoker;
import org.calculator.verification.Verifier;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

class EntryPanel extends Panel implements CompositePanel {
	private JPanel panel;
	private Panel appender, eraser, functions;

	public EntryPanel(Verifier aErrorVerifier, Invoker invoker, Observer observer){
		createTextArea();
		panel = new JPanel();
		setPanels(aErrorVerifier, invoker, observer);
		addPanels(groupLayout());
	}

	public JPanel panel(){
		return panel;
	}

	private void createTextArea(){
		textArea = new JTextArea();
		textArea.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	}
	private void setPanels(Verifier aErrorVerifier, Invoker invoker, Observer observer){
		appender = new AppenderPanel();
		eraser = new EraserPanel();
		functions = new FunctionsPanel(aErrorVerifier, invoker, observer);
		Panel[] panels = {appender, eraser, functions};
		Arrays.stream(panels).forEach(aPanel -> aPanel.setTextArea(textArea));
	}

	private void addPanels(GroupLayout layout){
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(textArea)
				.addGroup(
						layout.createSequentialGroup()
						.addComponent(appender)
						.addComponent(eraser)
						.addComponent(functions)
				)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(textArea)
				.addGroup(
						layout.createParallelGroup()
						.addComponent(appender)
						.addComponent(eraser)
						.addComponent(functions)
				)
		);
	}

	private GroupLayout groupLayout(){
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		return layout;
	}
}
