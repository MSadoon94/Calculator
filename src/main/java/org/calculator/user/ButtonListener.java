package org.calculator.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {
	private Panel panel;
	public ButtonListener(Panel panel){
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		actionSet().consumers().keySet().forEach((actionEvent) -> {
			if (e.getSource() == actionEvent){
				actionSet().consumers().get(actionEvent);
			}
		});
	}
	private ActionSet actionSet(){
		return panel.actions();
	}

}
