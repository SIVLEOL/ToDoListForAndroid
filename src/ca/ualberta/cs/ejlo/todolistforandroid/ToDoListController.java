package ca.ualberta.cs.ejlo.todolistforandroid;

import java.io.IOException;

public class ToDoListController {
	
	private static ToDoList toDoList = null;
	// Warning: Throws a runTimeException
	static public ToDoList getToDoList(){
		if(toDoList == null){
			try {
				toDoList = ToDoListManager.getManager().loadToDoList();
				toDoList.addListener(new Listener(){
					@Override
					public void update(){
						saveToDoList();
					}
					
				});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ToDoList from ToDoListManager");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ToDoList from ToDoListManager");
			}
		}
		return toDoList;
	}
	
	static public void saveToDoList(){
		try {
			ToDoListManager.getManager().saveToDoList(getToDoList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ToDoList from ToDoListManager");
		}
	}
	
	public void addToDoItem(ToDoItem toDoItem) {
		getToDoList().addItem(toDoItem);
	}
}
