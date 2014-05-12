package com.framgia.fels.activities;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
	private ImageView avatar;
	private TextView tvName;
	private TextView tvLearnedCounts;
	String name = "temp";
	int learnedCounts = 99;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_users_detail);
		new FetchDatabaseTask().execute(1);
		avatar = (ImageView)findViewById(R.id.avatar_image);
		avatar.setImageResource(R.drawable.ic_launcher);
		tvName = (TextView) findViewById(R.id.tv_name);
		
		
		tvLearnedCounts = (TextView)findViewById(R.id.tv_learned_words_count);
		
			  
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
			//SystemClock.sleep(2000);
			FelsDatabaseHelper database = new FelsDatabaseHelper(ShowUserDetailActivity.this);
			
			
			
			
			
			database.insertUsers(1, "username 1", "user 1's avatar", "user 1's email", "password");
			database.insertUsers(2, "username 2", "user 2's avatar", "user 2's email", "password");
			database.insertUsers(3, "username 3", "user 3's avatar", "user 3's email", "password");
			
			database.insertCategories(1, "Girls");
			database.insertCategories(2, "Boys");
			
			database.insertWords(1, "word 1", "meaning 1", 3, "sound 1", 1);
			database.insertWords(2, "word 2", "meaning 2", 2, "sound 2", 2);
			database.insertWords(3, "word 3", "meaning 3", 1, "sound 3", 2);
			database.insertWords(4, "word 4", "meaning 4", 2, "sound 4", 2);
			database.insertWords(5, "word 5", "meaning 5", 2, "sound 5", 1);
			database.insertWords(6, "word 6", "meaning 6", 2, "sound 6", 1);
			
			database.insertLessons(1, 1, "date 1", 1);
			database.insertLessons(2, 1, "date 2", 2);
			database.insertLessons(3, 1, "date 2", 2);
			database.insertLessons(4, 1, "date 3", 1);
			
			database.insertLessonResults(1, 1, 3);
			database.insertLessonResults(2, 3, 1);
			database.insertLessonResults(3, 4, 2);
			database.insertLessonResults(4, 5, 2);
			
			
			lessons = database.getLessonListForUser(database.getUser(params[0]));
			name = database.getUser(1).getName();
			learnedCounts = database.getLearnedWordCounts(database.getUser(1));
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {																																																																																																																																																																																																				
			// TODO Auto-generated method stub
			super.onPostExecute(result);	
			adapter = new LessonAdapter(ShowUserDetailActivity.this, lessons);
			
			lv = (ListView)findViewById(R.id.lesson_listview);
			lv.setAdapter(adapter);
			
			
			tvName.setText(name);
			tvLearnedCounts.setText("Learned " + learnedCounts + " words");
			if( dialog != null){
				
				dialog.dismiss();
			}
			Toast.makeText(ShowUserDetailActivity.this, ""+lessons.size(), Toast.LENGTH_SHORT).show();
			
		}
		
	}
}
