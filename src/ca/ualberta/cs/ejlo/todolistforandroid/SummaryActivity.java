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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	
	/*Summarizes content of to do list and archive list.
	 *Return to main activity or archive activity
	 *can be done through hardware back button.
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
		ToDoListManager.initManager(this.getApplicationContext());
		
		int toDoLen;
		int archiveLen;
		int toDoCheckCount = 0;
		int archiveCheckCount = 0;
		
		TextView toDoSummary = (TextView) findViewById(R.id.toDoSummary);
		TextView toDoChecked = (TextView) findViewById(R.id.toDoChecked);
		TextView toDoNotChecked = (TextView) findViewById(R.id.toDoNotChecked);
		TextView archiveSummary = (TextView) findViewById(R.id.archiveSummary);
		TextView archiveChecked = (TextView) findViewById(R.id.archiveChecked);
		TextView archiveNotChecked = (TextView) findViewById(R.id.archiveNotChecked);
		TextView totalSummary = (TextView) findViewById(R.id.totalSummary);
		
		Collection<ToDoItem> tempList = ToDoListController.getToDoList().getToDoItems();
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);
        
        Collection<ToDoItem> tempList2 = ToDoListController.getToDoList().getArchiveItems();
        ArrayList<ToDoItem> archiveList = new ArrayList<ToDoItem>(tempList2);
        
        toDoLen = toDoList.size();
        archiveLen = archiveList.size();
        
        for (int i = 0; i < toDoLen; i++){
			if (toDoList.get(i).getCheck() == 1){
				toDoCheckCount = toDoCheckCount + 1;
			}
		}
        
        for (int i = 0; i < archiveLen; i++){
			if (archiveList.get(i).getCheck() == 1){
				archiveCheckCount = archiveCheckCount + 1;
			}
		}
        
        toDoSummary.setText("To Do Items: " + toDoLen);
        toDoChecked.setText("Checked: " + toDoCheckCount);
        toDoNotChecked.setText("Unchecked: " + (toDoLen - toDoCheckCount));
        archiveSummary.setText("Archived Items: " + archiveLen);
        archiveChecked.setText("Checked: " + archiveCheckCount);
        archiveNotChecked.setText("Unchecked: " + (archiveLen - archiveCheckCount));
        totalSummary.setText("Total Items: " + (toDoLen + archiveLen));
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
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
}
