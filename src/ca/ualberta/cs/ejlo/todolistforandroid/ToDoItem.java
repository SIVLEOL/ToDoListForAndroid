package ca.ualberta.cs.ejlo.todolistforandroid;

public class ToDoItem {

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
	
}
