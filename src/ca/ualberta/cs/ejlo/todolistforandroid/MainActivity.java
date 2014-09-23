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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView toDoListView = (ListView) findViewById(R.id.ToDoListView);
        Collection<ToDoItem> tempList = ToDoListController.getToDoList().getToDoItems();
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>(tempList);
        final ArrayAdapter<ToDoItem> toDoListAdapter = new ArrayAdapter<ToDoItem>(this, android.R.layout.simple_list_item_1, toDoList);
        toDoListView.setAdapter(toDoListAdapter);
        
        ToDoListController.getToDoList().addListener(new Listener(){
        	public void update(){
        		toDoListAdapter.notifyDataSetChanged();
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void goToArchive(MenuItem menu){
    	Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
    	startActivity(intent);
    }
    
    public void goToArchive(View v){
    	Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
    	startActivity(intent);
    }
    
    public void addToDoItemAction(View v){
    	ToDoListController ct = new ToDoListController();
    	EditText addTextView = (EditText) findViewById(R.id.AddToDoItemText);
    	ct.addToDoItem(new ToDoItem(addTextView.getText().toString()));
    	addTextView.setText("");
    	//connect to text later
    }
    
    public void deleteToDoItem(MenuItem menu){
    	Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show();
    }
    
    public void emailTest(View v){
    	Toast.makeText(this, "Email Test", Toast.LENGTH_SHORT).show();
    }
    
    
}
