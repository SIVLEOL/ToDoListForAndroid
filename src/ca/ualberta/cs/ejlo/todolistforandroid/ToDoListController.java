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

import java.io.IOException;

public class ToDoListController {
	
	/*Allows activities to access shared to do lists
	 *
	 * Code format heavily inspired by Abram Hindle's student picker program.
	 */
	
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
	
	public void addArchiveItem(ToDoItem toDoItem) {
		getToDoList().addArchiveItem(toDoItem);
	}
}
