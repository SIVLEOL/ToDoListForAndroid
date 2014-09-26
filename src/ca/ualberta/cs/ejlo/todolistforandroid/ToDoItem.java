/*
	ejlo-notes: A simple to do list for android
    Copyright (C) 2014  ejlo@ualberta.ca

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    */

package ca.ualberta.cs.ejlo.todolistforandroid;

import java.io.Serializable;

public class ToDoItem implements Serializable{
	
	//A simple ToDoItem that can be checked and unchecked.

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
		checked = 1;
	}
	
	public void uncheck(){
		checked = 0;
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
