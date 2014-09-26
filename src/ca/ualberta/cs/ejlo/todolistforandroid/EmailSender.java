package ca.ualberta.cs.ejlo.todolistforandroid;

import android.content.Context;
import android.content.Intent;
//import android.util.Log;
import android.widget.Toast;

public class EmailSender {
	
	public EmailSender(){
		
	}
	
	public void sendEmail(Context context, String subject, String body, String recipient){
		//Format taken from: http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		intent.putExtra(Intent.EXTRA_EMAIL, recipient);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, body);
		
		/*
		Log.println(Log.WARN, "emailTest", recipient);
		Log.println(Log.WARN, "emailTest", subject);
		Log.println(Log.WARN, "emailTest", body);
		*/
		
		try {
			context.startActivity(Intent.createChooser(intent, "Send email..."));
		} catch (android.content.ActivityNotFoundException e) {
			Toast.makeText(context, "No email clients found on system", Toast.LENGTH_SHORT).show();
		}
	}
	
}
