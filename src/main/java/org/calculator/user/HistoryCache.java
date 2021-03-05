package org.calculator.user;

import org.calculator.common.Request;

import java.util.*;

class HistoryCache implements UserCache{
	private ArrayList<Integer> inputOrder = new ArrayList<>();
	private ListIterator<Integer> iterator;
	private HashMap<Integer, Request> requests = new HashMap<>();

	public void addRequest(Request request) {
		inputOrder.add(request.hashCode());
		requests.put(request.hashCode(), request);
		setIterator();
	}
	private void setIterator(){
		iterator = inputOrder.listIterator();
		while(iterator.nextIndex() != inputOrder.size()){
			iterator.next();
		}
	}

	public boolean hasNext(){
		return iterator.hasNext();
	}

	public boolean hasPrevious(){
		return iterator.hasPrevious();
	}

	public Request next(){
		int next = iterator.next();
		if (iterator.nextIndex() == inputOrder.size()){
			next = (iterator.hasPrevious()) ? iterator.previous() : next;
		}
		return requests.get(next);
	}

	public Request previous(){
		int previous = iterator.previous();
		if (iterator.previousIndex() == -1){
			previous = iterator.next();
		}
		return requests.get(previous);
	}
}
