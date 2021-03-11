package org.calculator.user;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.function.Consumer;

class ActionSet {
	private HashMap<ActionEvent, Consumer<Object> > consumerActions = new HashMap<>();

	public void addConsumerAction(ActionEvent action, Consumer<Object> consumer){
		consumerActions.put(action, consumer);
	}
	public HashMap<ActionEvent, Consumer<Object> > consumers(){
		return consumerActions;
	}
}
