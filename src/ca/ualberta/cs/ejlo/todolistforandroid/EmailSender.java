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

import android.content.Context;
import android.content.Intent;
//import android.util.Log;
import android.widget.Toast;

public class EmailSender {
	
	//Sends emails through intent
	
	public EmailSender(){
		
	}
	
	public void sendEmail(Context context, String subject, String body, String recipient){
		//Format taken from: fiXedd's comment at 
		//http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
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
