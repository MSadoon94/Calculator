package org.calculator.user;

import org.calculator.processing.Invoker;
import org.calculator.verification.Verifier;

import javax.swing.*;
import java.awt.*;

class EntryPanel extends Panel implements CompositePanel {
	private JPanel panel;

	public EntryPanel(Verifier aErrorVerifier, Invoker invoker, Observer observer){
		panel = new JPanel(new GridLayout(2, 2));
		panel.add(textArea);
		addPanels(
				new AppenderPanel(),
				new EraserPanel(),
				new FunctionsPanel(aErrorVerifier, invoker, observer)
		);
	}

	public JPanel panel(){
		return panel;
	}

	private void addPanels(Panel...panels){
		for (Panel aPanel : panels) {
			panel.add(aPanel);
		}
	}
}
