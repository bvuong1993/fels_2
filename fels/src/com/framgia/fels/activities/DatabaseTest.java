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
		FelsDatabaseHelper database = new FelsDatabaseHelper(this);
//		database.insertUsers(3, "thanh", "thanh's avatar", "thanh@gmail.com", "thanh");
		//database.insertLessons(1, 1, "date1");
	//	database.insertFollow(2, 3);
	//	database.insertLessons(2, 3, "date1");
		//database.insertLessons(3, 3, "date3");
	//Log.i("COUNT" , 1+"");
		
		List<Lesson> list = database.getLessonListForHome(database.getUser(1));
	//	System.out.println(database.getUser(3).getName().toString()+"AAAAAAAAA");
		Toast.makeText(this, list.size() +"", Toast.LENGTH_SHORT).show();
	}
	
}
