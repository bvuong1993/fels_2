package com.framgia.fels.adapters;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fels.R;
import com.framgia.fels.activities.CategoryActivity;
import com.framgia.fels.activities.DatabaseTest;
import com.framgia.fels.activities.GlobalVariables;
import com.framgia.fels.activities.LessonActivity;
import com.framgia.fels.adapters.LessonAdapter.ViewHolder;
import com.framgia.fels.models.*;

public class CategoryAdapter extends BaseAdapter {
	Context context;
	List<Category> categories;

	public CategoryAdapter(Context context, List<Category> categories) {
		this.context = context;
		this.categories = categories;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categories.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return categories.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		Category entry = categories.get(position);


		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.category_item, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			viewHolder = new ViewHolder();

			viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title_textView);
			viewHolder.startButton = (Button) convertView.findViewById(R.id.start_button);


			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();	
		}

		//	viewHolder.imageView.setBackgroundResource(R.drawable.ic_launcher);
		//	viewHolder.textView.setText("Learned 20 words in Lesson \"" + entry.getCategory().getCategoryName() + "\" - (" + entry.getDate() + ")");
		viewHolder.titleTextView.setText(entry.getCategoryName());
		viewHolder.startButton.setTag(entry);
		viewHolder.startButton.setOnClickListener(new MyOnClickListener(context));
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
			Category category = (Category) button.getTag();
			Intent i = new Intent(context, LessonActivity.class);		
			//i.putExtra(LessonActivity.CATEGORY_TAG, (Serializable) category); //error here
			GlobalVariables.categoryToTest = category;
			context.startActivity(i);
			
//			Intent i = new Intent(button.getContext(), LessonActivity.class);
			//i.putExtra(LessonActivity.CATEGORY_TAG, category);
//			button.getContext().startActivity(i);

		}
	}
	
	static class ViewHolder{
		TextView titleTextView;
		Button startButton;
	}
}
