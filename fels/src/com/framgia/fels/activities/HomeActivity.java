package com.framgia.fels.activities;

import java.util.Iterator;
import java.util.List;

import com.example.fels.R;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Lesson;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		ListView lessonViews = (ListView) findViewById(R.id.recent_activities_listview);
		FelsDatabaseHelper database = new FelsDatabaseHelper(this);
		List<Lesson> lessons = database.getLessonListForHome(database.getUser(1));
		
		String[] lessonStrings = new String[lessons.size()];
		
		
		int i =0;
		for (Iterator<Lesson> iter = lessons.iterator(); iter.hasNext(); i++) {
			Lesson lesson = iter.next();
			lessonStrings[i] = "learn at " + lesson.getDate().toString();
		}
				
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.home_lesson_view, lessonStrings);
		lessonViews.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
