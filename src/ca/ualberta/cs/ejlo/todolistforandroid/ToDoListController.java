package ca.ualberta.cs.ejlo.todolistforandroid;

public class ToDoListController {
	
	private static ToDoList toDoList = null;
	
	static public ToDoList getToDoList(){
		if(toDoList == null){
			toDoList = new ToDoList();
		}
		return toDoList;
	}
	
	public void addToDoItem(ToDoItem toDoItem) {
		getToDoList().addItem(toDoItem);
	}
}
