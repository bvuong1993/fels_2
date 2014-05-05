package com.framgia.fels.activities;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fels.R;
import com.framgia.fels.adapters.LessonAdapter;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Lesson;


public class ShowUserDetailActivity extends Activity{
	private ProgressDialog dialog;
	private List<Lesson> lessons;
	private LessonAdapter adapter;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_users_detail);
		new FetchDatabaseTask().execute(1);
		
			
	}	
	
	class FetchDatabaseTask extends AsyncTask<Integer, Void, Void>{
		
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ShowUserDetailActivity.this, "", "Loading...");
			
		}

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			SystemClock.sleep(2000);
			FelsDatabaseHelper database = new FelsDatabaseHelper(ShowUserDetailActivity.this);
			
			
			/*database.insertUsers(1, "user1", "avatar1", "email1", "password1");
			database.insertLessons(1, 1, "date1",1);
			database.insertLessons(2, 1, "date2",1);*/
			//database.insertLessons(3, 1, "date3",1);
			//database.insertCategories(1, "Girls");
			
			lessons = database.getLessonListForUser(database.getUser(params[0]));
			
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {																																																																																																																																																																																																				
			// TODO Auto-generated method stub
			super.onPostExecute(result);	
			adapter = new LessonAdapter(ShowUserDetailActivity.this, lessons);
			
			lv = (ListView)findViewById(R.id.lesson_listview);
			lv.setAdapter(adapter);
			
			if( dialog != null){
				
				dialog.dismiss();
			}
			Toast.makeText(ShowUserDetailActivity.this, ""+lessons.size(), Toast.LENGTH_SHORT).show();
			
		}
		
	}
}
