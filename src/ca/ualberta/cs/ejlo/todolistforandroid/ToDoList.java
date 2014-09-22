package ca.ualberta.cs.ejlo.todolistforandroid;

import java.util.ArrayList;
import java.util.Collection;

public class ToDoList {

	protected ArrayList<ToDoItem> toDoList;
	private int checkedCount = 0;
	private int totalCount = 0;
	
	public ToDoList(){
		toDoList = new ArrayList<ToDoItem>();
	}
	public Collection<ToDoItem> getToDoItems() {
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

}
