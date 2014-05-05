package com.framgia.fels.activities;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fels.R;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Lesson;
import com.framgia.fels.models.User;

public class DatabaseTest extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_test);
		
		//createSampleDatabase();
		FelsDatabaseHelper database = new FelsDatabaseHelper(this);
//		database.insertUsers(3, "thanh", "thanh's avatar", "thanh@gmail.com", "thanh");
		//database.insertLessons(1, 1, "date1");
	//	database.insertFollow(2, 3);
	//	database.insertLessons(2, 3, "date1");
		//database.insertLessons(3, 3, "date3");
	//Log.i("COUNT" , 1+"");
		
		List<Lesson> list = database.getLessonListForHome(database.getUser(2));
	//	System.out.println(database.getUser(3).getName().toString()+"AAAAAAAAA");
		Toast.makeText(this, list.size() +"", Toast.LENGTH_SHORT).show();
	}
	
	public void createSampleDatabase() {
		FelsDatabaseHelper database = new FelsDatabaseHelper(this);
		database.insertUsers(1, "user1", "avatar1", "email1", "password1");
		database.insertLessons(1, 1, "date1",1);
		database.insertLessons(2, 1, "date2",1);
		database.insertLessons(3, 1, "date3",1);
		database.insertCategories(1, "Girls");
		database.insertCategories(2, "Boys");
		database.insertCategories(3, "Gays");
		database.insertUsers(2, "user2", "avatar2", "email2", "password2");
		database.insertFollow(2, 1);
	}
	
}
