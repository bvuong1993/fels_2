package com.framgia.fels.adapters;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fels.R;
import com.framgia.fels.models.Lesson;

public class LessonAdapter extends BaseAdapter{
	private Context context;
	private List<Lesson> lessons;
	
	public LessonAdapter(Context context, List<Lesson> lessons){
		this.context = context;
		this.lessons = lessons;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lessons.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lessons.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		Lesson entry = lessons.get(position);
		
		
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.lesson_item, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.user_imageview);
			
			viewHolder.textView = (TextView)convertView.findViewById(R.id.content_textview);
			
			
			convertView.setTag(viewHolder);
		} else viewHolder = (ViewHolder) convertView.getTag();	
			
		viewHolder.imageView.setBackgroundResource(R.drawable.ic_launcher);
		viewHolder.textView.setText("Learned 20 words in Lesson \"" + entry.getCategory().getCategoryName() + "\" - (" + entry.getDate() + ")");
		return convertView;
	}
	
	static class ViewHolder{
		ImageView imageView;
		TextView textView;
	}

}
