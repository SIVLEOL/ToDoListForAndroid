package ca.ualberta.cs.ejlo.todolistforandroid;

import java.io.Serializable;

public class ToDoItem implements Serializable{

	/**
	 * ToDoItem serialization_ID
	 */
	private static final long serialVersionUID = 5134096143323535366L;
	private int checked = 0;
	private String text;

	public ToDoItem(String text){
		this.text = text;
	}
	
	public void check(){
	//check or uncheck the to do item
		if (checked == 0){
			checked = 1;
		} else {
			checked = 0;	
		}
	}
	
	public int getCheck(){
		return checked;
	}
	
	public String getText(){
		return text;
	}
	
	public String toString(){
		return getText();
	}
	
	public boolean equals(Object compareToDoItem){
		if(compareToDoItem == null){
			return false;
		}
		if(compareToDoItem.getClass()==this.getClass()){
			return this.equals((ToDoItem)compareToDoItem);
		} else {
			return false;
		}
	}
	
	public boolean equals(ToDoItem compareToDoItem){
		if(compareToDoItem == null){
			return false;
		}
		return getText().equals(compareToDoItem.getText());
	}
	@Override
	public int hashCode(){
		return ("ToDoItem:"+getText()).hashCode();
	}
	
}
