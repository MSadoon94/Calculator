package org.calculator.user;

import org.calculator.processing.Invoker;
import org.calculator.verification.Verifier;

import javax.swing.*;

class EntryPanel extends Panel implements CompositePanel {
	private JPanel panel;
	private Panel appender, eraser, functions;

	public EntryPanel(Verifier aErrorVerifier, Invoker invoker, Observer observer){
		panel = new JPanel();
		panel.add(textArea);
		setPanels(aErrorVerifier, invoker, observer);
		addPanels(groupLayout());
	}

	public JPanel panel(){
		return panel;
	}
	private void setPanels(Verifier aErrorVerifier, Invoker invoker, Observer observer){
		appender = new AppenderPanel();
		eraser = new EraserPanel();
		functions = new FunctionsPanel(aErrorVerifier, invoker, observer);

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
