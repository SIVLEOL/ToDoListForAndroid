package ca.ualberta.cs.ejlo.todolistforandroid;

import java.util.ArrayList;
import java.util.Collection;

public class ToDoList {

	protected ArrayList<ToDoItem> toDoList;
	
	public ToDoList(){
		toDoList = new ArrayList<ToDoItem>();
	}
	
	public Collection getToDoItems() {
		return toDoList;
	}

	public void addItem(ToDoItem testItem) {
		toDoList.add(testItem);
	}

	public void removeItem(ToDoItem testItem) {
		toDoList.remove(testItem);
	}

}
