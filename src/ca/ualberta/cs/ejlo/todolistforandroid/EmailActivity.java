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

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class EmailActivity extends Activity {
	
	/*Allows user to access email options and input the recipient
	 * into a text field. Return to main activity or archive activity
	 * can be done through hardware back button.
	 */
	
	public final static String EXTRA_MESSAGE = "ca.ualberta.cs.ejlo.todolistforandroid.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		
		ToDoListManager.initManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void emailAllToDo(View v){
		//Email entire to do list but not archive
		TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
		EmailSender emailSender = new EmailSender();
		Collection<ToDoItem> tempList = ToDoListController.getToDoList().getToDoItems();
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);
        String body = "";
        for (int i = 0; i < toDoList.size(); i++){
        	ToDoItem tempItem = toDoList.get(i);
        	if (tempItem.getCheck() == 1){
        		body = body + "X|";
        	} else {
        		body = body + " |";
        	}
        	body = body + tempItem.getText() + "\n";
        }
        emailSender.sendEmail(EmailActivity.this, "ejlo-notes: To Do List", body, emailTextView.getText().toString());
	}
	
	public void emailSomeToDo(View v){
		//Go to emailToDoActivity to select To Do Items to email
		Intent intent = new Intent(EmailActivity.this, EmailToDoActivity.class);
		TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
		String recipient = emailTextView.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, recipient);
		startActivity(intent);
	}
	
	public void emailSomeArchive(View v){
		//Go to emailArchiveActivity to select archive items to email
		Intent intent = new Intent(EmailActivity.this, EmailArchiveActivity.class);
		TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
		String recipient = emailTextView.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, recipient);
		startActivity(intent);
	}
	
	public void emailAll(View v){
		//Email entire to do list and archive 
		TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
		EmailSender emailSender = new EmailSender();
		Collection<ToDoItem> tempList = ToDoListController.getToDoList().getToDoItems();
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);
        Collection<ToDoItem> tempList2 = ToDoListController.getToDoList().getArchiveItems();
        ArrayList<ToDoItem> archiveList = new ArrayList<ToDoItem>(tempList2);
        String body = "To Do List:\n";
        for (int i = 0; i < toDoList.size(); i++){
        	ToDoItem tempItem = toDoList.get(i);
        	if (tempItem.getCheck() == 1){
        		body = body + "X|";
        	} else {
        		body = body + " |";
        	}
        	body = body + tempItem.getText() + "\n";
        }
        body = body + "\nArchive:\n";
        for (int i = 0; i < archiveList.size(); i++){
        	ToDoItem tempItem = archiveList.get(i);
        	if (tempItem.getCheck() == 1){
        		body = body + "X|";
        	} else {
        		body = body + " |";
        	}
        	body = body + tempItem.getText() + "\n";
        }
        emailSender.sendEmail(EmailActivity.this, "ejlo-notes: To Do List and Archive", body, emailTextView.getText().toString());
	}
}
