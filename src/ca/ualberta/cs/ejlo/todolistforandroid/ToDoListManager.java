package ca.ualberta.cs.ejlo.todolistforandroid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ToDoListManager {
	static final String prefFile = "ToDoList";
	static final String slkey = "toDoList";
	
	Context context;
	
	public ToDoListManager(Context context) {
		this.context = context;
	}
	public ToDoList loadToDoList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String toDoListData = settings.getString(slkey, "");
		if (toDoListData.equals("")){
			return new ToDoList();
		} else {
			return toDoListFromString(toDoListData);
		}
	}
	static public ToDoList toDoListFromString(String toDoListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(toDoListData, Base64.DEFAULT));
		ObjectInputStream ois = new ObjectInputStream(bais);
		return (ToDoList) ois.readObject();
	}
	public void saveToDoList(ToDoList toDoList) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slkey, toDoListToString(toDoList));
		editor.commit();
	}
	static public String toDoListToString(ToDoList toDoList) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(toDoList);
		oos.close();
		oos.flush();
		byte bytes[] = baos.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
}
