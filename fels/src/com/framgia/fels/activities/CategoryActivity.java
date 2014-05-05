package com.framgia.fels.activities;

import java.util.ArrayList;
import java.util.List;

import com.example.fels.R;
import com.framgia.fels.activities.ShowUserDetailActivity.FetchDatabaseTask;
import com.framgia.fels.adapters.CategoryAdapter;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Category;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.framgia.fels.adapters.*;

public class CategoryActivity extends Activity {

	List<Category> categories;
	public void createSampleDatabase() {
		FelsDatabaseHelper database = new FelsDatabaseHelper(getBaseContext());
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		//new DatabaseTest().createSampleDatabase();
		//createSampleDatabase();
		new FetchDatabaseTask(this).execute(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	class FetchDatabaseTask extends AsyncTask<Integer, Void, Boolean> {
		ProgressDialog dialog;
		Context context;
		
		public FetchDatabaseTask(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(CategoryActivity.this, "", "Loading");
		}
		@Override
		protected Boolean doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			FelsDatabaseHelper database = new FelsDatabaseHelper(context);
			categories = database.getCategoryListInClass();
			
			SystemClock.sleep(1000);
			
			if (categories != null)
				return true;
			else
				return false;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub	
						
			super.onPostExecute(result);			
			dialog.dismiss(	);
			
			if (result.booleanValue()) {
				CategoryAdapter adapter = new CategoryAdapter(context, categories);
				ListView lv = (ListView) findViewById(R.id.categories_listView);
				lv.setAdapter(adapter);
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);  
				builder.setMessage("FAIL!")
					.setTitle("Get Categories")
					.setPositiveButton("OK", null);
				AlertDialog alert = builder.create();
				alert.show();
			}
		}			
	}
}
