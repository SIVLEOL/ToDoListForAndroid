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
	protected ArrayList<ToDoItem> archiveList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ToDoList(){
		toDoList = new ArrayList<ToDoItem>();
		archiveList = new ArrayList<ToDoItem>();
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
	public Collection<ToDoItem> getArchiveItems() {
		return archiveList;
	}
	public void addItem(ToDoItem Item) {
		toDoList.add(Item);
		notifyListeners();
	}
	public void addArchiveItem(ToDoItem Item) {
		archiveList.add(Item);
		notifyListeners();
	}
	public void removeItem(ToDoItem Item) {
		toDoList.remove(Item);
		notifyListeners();
	}
	public void removeArchiveItem(ToDoItem Item) {
		archiveList.remove(Item);
		notifyListeners();
	}
	//Change this to move to archive and move back
	public ToDoItem popItem(ToDoItem Item){
		ToDoItem tempItem = Item;
		toDoList.remove(Item);
		return tempItem;
	}
	public void checkItem(int position){
		toDoList.get(position).check();
		notifyListeners();
	}
	public void uncheckItem(int position){
		toDoList.get(position).uncheck();
		notifyListeners();
	}
	public void checkArchiveItem(int position){
		archiveList.get(position).check();
		notifyListeners();
	}
	public void uncheckArchiveItem(int position){
		archiveList.get(position).uncheck();
		notifyListeners();
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
