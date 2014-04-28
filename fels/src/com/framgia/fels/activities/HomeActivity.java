package com.framgia.fels.activities;

import com.example.fels.R;

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
		String array[] = {"vuong", "vuong2", "vuong3"};
		
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.home_lesson_view, array);
		lessonViews.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
