package ca.ualberta.cs.ejlo.todolistforandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ArchiveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archive);
		
		ToDoListManager.initManager(this.getApplicationContext());
		
		
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
	
	public void addToDoItemAction(View v){
    	ToDoListController ct = new ToDoListController();
    	EditText addTextView = (EditText) findViewById(R.id.AddToDoItemText);
    	ct.addToDoItem(new ToDoItem(addTextView.getText().toString()));
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
