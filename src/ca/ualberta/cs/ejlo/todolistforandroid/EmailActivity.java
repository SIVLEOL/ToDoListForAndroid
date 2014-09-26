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
		Intent intent = new Intent(EmailActivity.this, EmailToDoActivity.class);
		TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
		String recipient = emailTextView.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, recipient);
		startActivity(intent);
	}
	
	public void emailSomeArchive(View v){
		Intent intent = new Intent(EmailActivity.this, EmailArchiveActivity.class);
		TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
		String recipient = emailTextView.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, recipient);
		startActivity(intent);
	}
	
	public void emailAll(View v){
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
