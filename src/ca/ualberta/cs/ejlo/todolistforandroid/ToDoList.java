package ca.ualberta.cs.ejlo.todolistforandroid;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import ca.ualberta.cs.ejlo.todolistforandroid.Listener;

public class ToDoList implements Serializable {

	/**
	 * ToDoList serialization_ID
	 */
	private static final long serialVersionUID = -3208983109306905849L;
	protected ArrayList<ToDoItem> toDoList = null;
	private int checkedCount = 0;
	private int totalCount = 0;
	protected transient ArrayList<Listener> listeners = null;
	
	public ToDoList(){
		toDoList = new ArrayList<ToDoItem>();
		listeners = new ArrayList<Listener>();
	}
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public Collection<ToDoItem> getToDoItems() {
		return toDoList;
	}
	public void addItem(ToDoItem Item) {
		toDoList.add(Item);
		notifyListeners();
	}
	public void removeItem(ToDoItem Item) {
		toDoList.remove(Item);
		notifyListeners();
	}
	public ToDoItem popItem(ToDoItem Item){
		ToDoItem tempItem = Item;
		toDoList.remove(Item);
		return tempItem;
	}
	public int getCheckedCount(){
		return checkedCount;
	}
	public int getTotalCount(){
		return totalCount;
	}
	public void setCheckedCount(int count){
		checkedCount = count;
	}
	public void setTotalCount(int count){
		totalCount = count;
	}
	public void notifyListeners(){
		for (Listener listener : listeners){
			listener.update();
		}
	}
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	public void removeListener(Listener l) {
		listeners.remove(l);		
	}

}
