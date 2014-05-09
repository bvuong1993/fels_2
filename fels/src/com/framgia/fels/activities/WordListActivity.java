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
		}

		@Override
		protected List<String> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			FelsDatabaseHelper database = new FelsDatabaseHelper(WordListActivity.this);
			List<String> categoryList = database.getCategoryList();
			return categoryList;
		}
		
	}
	
	class FilterAsyncTask extends AsyncTask<Void, Void, List<Word>>{

		@Override
		protected List<Word> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			List<Word> wordList = new ArrayList<Word>();
			if( selectedRadioButton.getText().toString().compareTo("all") == 0){
				
				FelsDatabaseHelper database = new FelsDatabaseHelper(WordListActivity.this);
				/*
				database.insertWords(1, "jpWord1", "vnMeaning1", 0, "sound1", 1);
				database.insertWords(2, "jpWord2", "vnMeaning2", 0, "sound2", 1);
				database.insertWords(3, "jpWord3", "vnMeaning3", 0, "sound3", 1);
				database.insertWords(4, "jpWord4", "vnMeaning4", 0, "sound4", 1);
				database.insertWords(5, "jpWord5", "vnMeaning5", 0, "sound5", 1);
				database.insertWords(6, "jpWord6", "vnMeaning6", 0, "sound6", 1);
				*/
				wordList = database.getWordList(categorySpinner.getSelectedItem().toString());
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
				LinearLayout rowLinerLayout = new LinearLayout(WordListActivity.this);
				rowLinerLayout.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				rowLinerLayout.setOrientation(LinearLayout.HORIZONTAL);
				
				TextView jpTv = new TextView(WordListActivity.this);
				jpTv.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT, 1f));
				jpTv.setText(word.getJpWord());
				rowLinerLayout.addView(jpTv);
				
				TextView vnTv = new TextView(WordListActivity.this);
				vnTv.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT, 1f));
				vnTv.setText(word.getVnMeaning());
				rowLinerLayout.addView(vnTv);
				
				row.addView(rowLinerLayout);
				wordsTable.addView(row);						
			}
		}

		
		
	}

}
