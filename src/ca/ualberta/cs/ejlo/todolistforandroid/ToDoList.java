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

	public void addItem(ToDoItem Item) {
		toDoList.add(Item);
	}

	public void removeItem(ToDoItem Item) {
		toDoList.remove(Item);
	}
	
	public ToDoItem popItem(ToDoItem Item){
		ToDoItem tempItem = Item;
		toDoList.remove(Item);
		return tempItem;
	}

}
