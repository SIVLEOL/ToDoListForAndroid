package ca.ualberta.cs.ejlo.todolistforandroid;

import java.util.ArrayList;
import java.util.Collection;

import ca.ualberta.cs.ejlo.todolistforandroid.Listener;

public class ToDoList {

	protected ArrayList<ToDoItem> toDoList;
	private int checkedCount = 0;
	private int totalCount = 0;
	protected ArrayList<Listener> listeners;
	
	public ToDoList(){
		toDoList = new ArrayList<ToDoItem>();
		listeners = new ArrayList<Listener>();
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
		listeners.add(l);
	}
	public void removeListener(Listener l) {
		listeners.remove(l);		
	}

}
