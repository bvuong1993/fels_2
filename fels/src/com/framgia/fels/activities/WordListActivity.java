package com.framgia.fels.activities;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fels.R;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Word;

public class WordListActivity extends Activity{
	RadioGroup filterConditions;
	Spinner categorySpinner ;
	TableLayout wordsTable;
	ProgressDialog progressDialog;
	RadioButton selectedRadioButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_list);
		categorySpinner = (Spinner)findViewById(R.id.category_spinner);
		
		new categoryAsyncTask().execute();
		
		Button filterButton = (Button)findViewById(R.id.filter_button);
		filterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					new FilterAsyncTask().execute();
				
			}
		});
		
	}
	
	class categoryAsyncTask extends AsyncTask<Void, Void, List<String>>{
		
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = ProgressDialog.show(WordListActivity.this, "", "Loading...");
		}

		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if( progressDialog != null) progressDialog.dismiss();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(WordListActivity.this, android.R.layout.simple_dropdown_item_1line, result);
			categorySpinner.setAdapter(adapter);
			Toast.makeText(WordListActivity.this, result.size()+"" , Toast.LENGTH_SHORT).show();
		}

		@Override
		protected List<String> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			FelsDatabaseHelper database = new FelsDatabaseHelper(WordListActivity.this);
			
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
			database.insertLessons(3, 1, "date 2", 3);
			database.insertLessons(4, 1, "date 3", 1);
			
			database.insertLessonResults(1, 1, 3);
			database.insertLessonResults(2, 3, 1);
			database.insertLessonResults(3, 4, 2);
			database.insertLessonResults(4, 5, 2);
			
			List<String> categoryList = database.getCategoryList();
			return categoryList;
		}
		
	}
	
	class FilterAsyncTask extends AsyncTask<Void, Void, List<Word>>{

		@Override
		protected List<Word> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			List<Word> wordList = new ArrayList<Word>();
			FelsDatabaseHelper database = new FelsDatabaseHelper(WordListActivity.this);
			
			
			if( selectedRadioButton.getText().toString().compareTo("all") == 0){
				wordList = database.getWordList(categorySpinner.getSelectedItem().toString());
			} else if( selectedRadioButton.getText().toString().compareTo("learned") == 0){
				wordList = database.getLearnedWordList(categorySpinner.getSelectedItem().toString(), database.getUser(1));
			} else if( selectedRadioButton.getText().toString().compareTo("not learned") == 0){
				
				wordList = database.getNotLearnedWordList(categorySpinner.getSelectedItem().toString(), database.getUser(1));
				//System.out.println(wordList.size() + "BBBBBBBBBBBBB");
			}
			return wordList;
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = ProgressDialog.show(WordListActivity.this, "", "Loading...");
			filterConditions = (RadioGroup)findViewById(R.id.filter_conditions);
			selectedRadioButton = (RadioButton)findViewById(filterConditions.getCheckedRadioButtonId()); 
		}

		@Override
		protected void onPostExecute(List<Word> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if( progressDialog != null) progressDialog.dismiss();
			
			wordsTable = (TableLayout)findViewById(R.id.words_table);
			wordsTable.removeAllViews();
			
			for( Word word : result){
				TableRow row = new TableRow(WordListActivity.this);
				row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				
				TextView jpTv = new TextView(WordListActivity.this);
				jpTv.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT, 1f));
				jpTv.setText(word.getJpWord());
				row.addView(jpTv);
				
				TextView vnTv = new TextView(WordListActivity.this);
				vnTv.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT, 1f));
				vnTv.setText(word.getVnMeaning());
				
				row.addView(vnTv);
				wordsTable.addView(row);						
			}
		}

		
	}

}
