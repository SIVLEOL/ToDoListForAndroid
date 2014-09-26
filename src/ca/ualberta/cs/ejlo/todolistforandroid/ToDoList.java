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
import java.util.ArrayList;
import java.util.Collection;

import ca.ualberta.cs.ejlo.todolistforandroid.Listener;

public class ToDoList implements Serializable {
	
	/*A datastructure containing two arraylists containing ToDoItems, one for the 
	 * main to do list and one for the archive list.
	 */

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
	public void archiveItem(ToDoItem toDoItem) {
		toDoList.remove(toDoItem);
		addArchiveItem(toDoItem);
	}
	public void unarchiveItem(ToDoItem toDoItem) {
		archiveList.remove(toDoItem);
		addItem(toDoItem);
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
