package com.framgia.fels.activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.fels.R;
import com.framgia.fels.adapters.AnswerAdapter;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Category;
import com.framgia.fels.models.Lesson;
import com.framgia.fels.models.User;
import com.framgia.fels.models.Word;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LessonActivity extends Activity {
	
	public static final String CATEGORY_TAG = "category";
	Lesson lesson;

	Iterator<Word> iter;	
	int scores;
	Word currentWord;
	ListView wordViews;
	TextView scoresTextView;
	TextView wordTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	//	FelsDatabaseHelper database = new FelsDatabaseHelper(this);
//		database.insertWords(1, "jpWord1", "vnMeaning1", 0, "sound1", 1);
//		database.insertWords(2, "jpWord2", "vnMeaning2", 0, "sound2", 1);
//		database.insertWords(3, "jpWord3", "vnMeaning3", 0, "sound3", 1);
//		database.insertWords(4, "jpWord4", "vnMeaning4", 0, "sound4", 1);
//		database.insertWords(5, "jpWord5", "vnMeaning5", 0, "sound5", 2);
//		database.insertWords(6, "jpWord6", "vnMeaning6", 0, "sound6", 2);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lesson);
		Category category = GlobalVariables.categoryToTest;
				
		TextView tv = (TextView)findViewById(R.id.welcom_textView);
		tv.setText("Let's start lesson about " + category.getCategoryName());
//		int defaultUserID=1;
//		lesson = new Lesson();
//		User currentUser = GlobalVariables.currentUser;
//		lesson.setUser(currentUser);
//		lesson.setCategory(category);
		
		wordViews = (ListView) findViewById(R.id.words_listView);
		wordTextView = (TextView) findViewById(R.id.word_textView);
		scoresTextView = (TextView) findViewById(R.id.scores_textView);
		
		
		FelsDatabaseHelper database = new FelsDatabaseHelper(this);
		//List<Word> words = new ArrayList<Word>();
		final int MAX_WORDS= 2;
		List<Word> words= database.getWordListForCategory(category, MAX_WORDS, true);
		
		// ASSUME THERE IS AT LEAST ONE WORD
//		List<Word> words= database.getWordList(category.getCategoryName());
		scores = 0;
		iter = words.iterator();
		currentWord = iter.next();

		updateUI();
		//wordViews.setDescendantFocusability(ListView.FOCUS_BLOCK_DESCENDANTS);
//		
//		String[] wordStrings = new String[words.size()];
//		
		
//		int i =0;
//		for (Iterator<Word> iter = words.iterator(); iter.hasNext(); i++) {
//			Word word= iter.next();
//			wordStrings[i] = word.toString();
//		}				
//		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.home_lesson_view, wordStrings);
//		wordViews.setAdapter(mAdapter);		
		
		wordViews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long id) {
				// TODO Auto-generated method stub				
//				Toast.makeText(getBaseContext(), "Item clicked position " + pos + " id " + id,
//						Toast.LENGTH_SHORT).show();
				if (currentWord.getResultId() == id) {
					scores++;
				}
							
				if (! iter.hasNext()) {
					wordViews.setAdapter(null);
					wordTextView.setText("End of lesson");
					Toast.makeText(getBaseContext(), "TOTAL: " + scores,
							Toast.LENGTH_SHORT).show();					
				} else {
					currentWord = iter.next();
					updateUI();
				}
			}
		});
		
	}

	private void updateUI() {
		wordTextView.setText(currentWord.getJpWord());
		scoresTextView.setText("Score: " + scores);
		wordViews.setAdapter(new AnswerAdapter(getBaseContext(), currentWord));
	}
//	private class AnswerProcessing implements OnItemLongClickListener {
//		@Override
//		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//				int arg2, long arg3) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lesson, menu);
		return true;
	}

}
