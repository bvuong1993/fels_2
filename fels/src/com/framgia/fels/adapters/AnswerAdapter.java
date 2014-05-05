package com.framgia.fels.adapters;

import java.util.ArrayList;
import java.util.List;

import com.example.fels.R;
import com.framgia.fels.database.FelsDatabaseHelper;
import com.framgia.fels.models.Answer;
import com.framgia.fels.models.Word;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerAdapter extends BaseAdapter {

	Context context;
	Word word;
	List<Answer> answers;
	
	public AnswerAdapter(Context context, Word word) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.word = word;
		// 2 A.THANH: implement getAnsewrsForWord
		FelsDatabaseHelper database = new FelsDatabaseHelper(context);
		
		this.answers = database.getAnswerListForWord(word);				
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return answers.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return answers.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return ((Answer) getItem(arg0)).getChoice_id();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;		

		Answer ans = (Answer) getItem(position);
		String content = ans.getContent();
		
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.answer_item, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			viewHolder = new ViewHolder();

			viewHolder.answerButton = (TextView) convertView.findViewById(R.id.answer_button);


			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();	
		}

		//	viewHolder.imageView.setBackgroundResource(R.drawable.ic_launcher);
		//	viewHolder.textView.setText("Learned 20 words in Lesson \"" + entry.getCategory().getCategoryName() + "\" - (" + entry.getDate() + ")");
		viewHolder.answerButton.setText(content);
//		viewHolder.answerButton.setOnClickListener(new MyOnClickListener(context));
		return convertView;
	}

	private class MyOnClickListener implements OnClickListener {
		
		private Context context;
		public MyOnClickListener(Context context) {			
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		
		@Override
		public void onClick(View button) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "TEXT shown by the adapter", Toast.LENGTH_SHORT).show();
		}
	}
	
	static class ViewHolder{
		TextView answerButton;
	}
}