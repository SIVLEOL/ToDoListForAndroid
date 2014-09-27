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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ArchiveActivity extends Activity {

	/*The archive activity, same functionality as the main activity but
	 * for the archive list. Returning to main activity can be done via
	 * the hardware back button.
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		
		ToDoListManager.initManager(this.getApplicationContext());
		
		//Set up the to do list
        final ListView toDoListView = (ListView) findViewById(R.id.ArchiveListView);
        final Collection<ToDoItem> tempList = ToDoListController.getToDoList().getArchiveItems();
        final ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);
        
        final ArrayAdapter<ToDoItem> toDoListAdapter = new ArrayAdapter<ToDoItem>(this, 
        		android.R.layout.simple_list_item_multiple_choice, toDoList);
        toDoListView.setAdapter(toDoListAdapter);
        
        //Set up listener for changes to the to do list
        ToDoListController.getToDoList().addListener(new Listener(){
        	public void update(){
        		toDoList.clear();
        		toDoListAdapter.notifyDataSetChanged();
        		toDoList.addAll(tempList);
        		toDoListAdapter.notifyDataSetChanged();
        		//Update the checkboxes of listview to reflect changes to data
				for (int i = 0; i < toDoList.size(); i++){
					if (toDoList.get(i).getCheck() == 1){
						toDoListView.setItemChecked(i, true);
					} else {
						toDoListView.setItemChecked(i, false);
					}
				}
        	}
        });
        
        for (int i = 0; i < toDoList.size(); i++){
			if (toDoList.get(i).getCheck() == 1){
				toDoListView.setItemChecked(i, true);
			}
		}
        
        toDoListView.setOnItemLongClickListener(new OnItemLongClickListener() {
        	//Long clicking brings up the delete and unarchive options
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(ArchiveActivity.this);
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Toast.makeText(ArchiveActivity.this, "Deleted "+toDoList.get(finalPosition).toString(), 
								Toast.LENGTH_SHORT).show();
						ToDoItem toDoItem = toDoList.get(finalPosition);
						ToDoListController.getToDoList().removeArchiveItem(toDoItem);
					}
				});
				adb.setNegativeButton("Move to To Do", new OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Toast.makeText(ArchiveActivity.this, toDoList.get(finalPosition).toString()+" added to To Do List", 
								Toast.LENGTH_SHORT).show();
						ToDoItem toDoItem = toDoList.get(finalPosition);
						ToDoListController.getToDoList().unarchiveItem(toDoItem);
					}	
					
				});
				adb.show();
				return false;
			}
		});
        
        toDoListView.setOnItemClickListener(new OnItemClickListener() {
        	//Check to do item on click
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				//Self Note: view appears to be a CheckedTextView passed in as a view, casting it worked here
				CheckedTextView tempView = (CheckedTextView) view;
				boolean check = tempView.isChecked();
				if (check){
					ToDoListController.getToDoList().checkArchiveItem(position);
				} else {
					ToDoListController.getToDoList().uncheckArchiveItem(position);
				}
			}	
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
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
	
	public void addArchiveItemAction(View v){
    	ToDoListController ct = new ToDoListController();
    	EditText addTextView = (EditText) findViewById(R.id.ArchiveAddText);
    	ct.addArchiveItem(new ToDoItem(addTextView.getText().toString()));
    	addTextView.setText("");
    	
    }
    
    public void goToEmail(MenuItem menu){
    	Intent intent = new Intent(ArchiveActivity.this, EmailActivity.class);
    	startActivity(intent);
    }
    
    public void goToSummary(MenuItem menu){
    	Intent intent = new Intent(ArchiveActivity.this, SummaryActivity.class);
    	startActivity(intent);
    }
}
