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
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmailArchiveActivity extends Activity {
	
	/*Allows user to select archive items to email, on send button
	 * press this activity finishes so user returns to the email menu.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_archive);
		
		ToDoListManager.initManager(this.getApplicationContext());
		
		ListView toDoListView = (ListView) findViewById(R.id.emailArchiveListView);
		Collection<ToDoItem> tempList = ToDoListController.getToDoList().getArchiveItems();
        final ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);	
		ArrayAdapter<ToDoItem> toDoListAdapter = new ArrayAdapter<ToDoItem>(this, 
        		android.R.layout.simple_list_item_multiple_choice, toDoList);
        toDoListView.setAdapter(toDoListAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_archive, menu);
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
	
	public void emailSelection(View v){
		
		//Upon pressing the email selection button, format selected content and email
		
		ListView toDoListView = (ListView) findViewById(R.id.emailArchiveListView);
		EmailSender emailSender = new EmailSender();
		Collection<ToDoItem> tempList = ToDoListController.getToDoList().getArchiveItems();
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);
        String body = "";
        SparseBooleanArray sba = toDoListView.getCheckedItemPositions();
        for (int i = 0; i < toDoList.size(); i++){
        	if(sba.get(i) == true){
	        	ToDoItem tempItem = toDoList.get(i);
	        	if (tempItem.getCheck() == 1){
	        		body = body + "X|";
	        	} else {
	        		body = body + " |";
	        	}
	        	body = body + tempItem.getText() + "\n";
        	}
        }
        emailSender.sendEmail(EmailArchiveActivity.this, "ejlo-notes: Archive Selection", body, 
        		getIntent().getStringExtra(EmailActivity.EXTRA_MESSAGE));
        finish();
	}
}
