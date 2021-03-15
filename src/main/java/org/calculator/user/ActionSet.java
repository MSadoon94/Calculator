package org.calculator.user;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.function.Consumer;

class ActionSet {
	private HashMap<ActionEvent, Consumer<Object> > consumerActions = new HashMap<>();
	private HashMap<String, Consumer<Object>> commands = new HashMap<>();

	public void addConsumerAction(ActionEvent action, Consumer<Object> consumer){
		consumerActions.put(action, consumer);
		commands.put(action.getActionCommand(), consumer);
	}
	public HashMap<ActionEvent, Consumer<Object> > consumers(){
		return consumerActions;
	}
	public Consumer<Object> event(String command){
		return commands.get(command);
	}
}
