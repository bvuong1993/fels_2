package com.framgia.fels.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.framgia.fels.models.Category;
import com.framgia.fels.models.Lesson;
import com.framgia.fels.models.User;
import com.framgia.fels.models.Word;
import com.framgia.fels.utils.Variables;

public class FelsDatabaseHelper extends SQLiteOpenHelper{

	public FelsDatabaseHelper(Context context){
		super(context, Variables.DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table " + Variables.TABLE_USERS + "( "
							+ Variables.COLUMN_USERS_ID + " integer primary key " + ", "
							+ Variables.COLUMN_USERS_NAME +  " text" + ", "
							+ Variables.COLUMN_USERS_AVATAR + " text" + ", "
							+ Variables.COLUMN_USERS_PASSWORD + " text" + ", "
							+ Variables.COLUMN_USERS_EMAIL + " text "
							+ " ) "
							);
		
		db.execSQL("create table " + Variables.TABLE_FOLLOW + "( "
				+ Variables.COLUMN_FOLLOW_FOLLOWING_ID + " integer" + ", "
				+ Variables.COLUMN_FOLLOW_FOLLOWED_ID + " integer" + ", "
				+ " primary key (" + Variables.COLUMN_FOLLOW_FOLLOWING_ID + ", " + Variables.COLUMN_FOLLOW_FOLLOWED_ID + ")"
				+ " ) "
				);
		
		db.execSQL("create table " + Variables.TABLE_WORDS + "( "
				+ Variables.COLUMN_WORDS_ID + " integer primary key " + ", "
				+ Variables.COLUMN_WORDS_JP_WORD +  " text" + ", "
				+ Variables.COLUMN_WORDS_VN_MEANING + " text" + ", "
				+ Variables.COLUMN_WORDS_RESULT_ID+ " integer" + ", "
				+ Variables.COLUMN_WORDS_SOUND+ " text" + ", "
				+ Variables.COLUMN_WORDS_CATEGORY_ID + " integer "
				+ " ) "
				);
		
		db.execSQL("create table " + Variables.TABLE_CATEGORIES + "( "
				+ Variables.COLUMN_CATEGORIES_ID + " integer primary key " + ", "
				+ Variables.COLUMN_CATEGORIES_NAME + " text "
				+ " ) "
				);
		
		db.execSQL("create table " + Variables.TABLE_CHOICES + "( "
				+ Variables.COLUMN_CHOICES_ID + " integer primary key " + ", "
				+ Variables.COLUMN_CHOICES_WORD_ID +  " integer" + ", "
				+ Variables.COLUMN_CHOICES_CONTENT + " text"				
				+ " ) "
				);
		
		db.execSQL("create table " + Variables.TABLE_LESSONS+ "( "
				+ Variables.COLUMN_LESSONS_ID + " integer primary key " + ", "
				+ Variables.COLUMN_LESSONS_USER_ID +  " integer" + ", "
				+ Variables.COLUMN_LESSONS_DATE + " text" + ", "			
				+ Variables.COLUMN_LESSONS_CATEGORY_ID +  " integer"
				+ " ) "
				);
		
		db.execSQL("create table " + Variables.TABLE_LESSON_RESULTS+ "( "
				+ Variables.COLUMN_LESSON_RESULTS_ID + " integer primary key " + ", "
				+ Variables.COLUMN_LESSON_RESULTS_WORD_ID +  " integer" + ", "
				+ Variables.COLUMN_LESSON_RESULTS_CHOICE_ID + " integer"				
				+ " ) "
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_CATEGORIES);
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_CHOICES);
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_FOLLOW);
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_WORDS);
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_LESSONS);
		db.execSQL("DROP TABLE IF EXISTS " + Variables.TABLE_LESSON_RESULTS);
	    onCreate(db);
	}
	
	public boolean insertUsers( int id, String name, String avatar, String email, String password){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_USERS_ID, id);
	    contentValues.put(Variables.COLUMN_USERS_NAME, name);
	    contentValues.put(Variables.COLUMN_USERS_AVATAR, avatar);
	    contentValues.put(Variables.COLUMN_USERS_EMAIL, email);
	    contentValues.put(Variables.COLUMN_USERS_PASSWORD, password);
	    db.insert(Variables.TABLE_USERS, null, contentValues);
	    return true;
	}
	
	public boolean insertFollow( int followingId, int followedId){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_FOLLOW_FOLLOWING_ID, followingId);
	    contentValues.put(Variables.COLUMN_FOLLOW_FOLLOWED_ID, followedId);
	    db.insert(Variables.TABLE_FOLLOW, null, contentValues);
	    return true;
	}
	
	public boolean insertWords( int wordId, String jpWord, String vnMeaning, int resultId, String sound, int categoryId ){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_WORDS_ID, wordId);
	    contentValues.put(Variables.COLUMN_WORDS_JP_WORD, jpWord);
	    contentValues.put(Variables.COLUMN_WORDS_VN_MEANING, vnMeaning);
	    contentValues.put(Variables.COLUMN_WORDS_RESULT_ID, resultId);
	    contentValues.put(Variables.COLUMN_WORDS_SOUND, sound);
	    contentValues.put(Variables.COLUMN_WORDS_CATEGORY_ID, categoryId);
	    db.insert(Variables.TABLE_WORDS, null, contentValues);
	    return true;
	}
	
	public boolean insertCategories( int categoryId, String name){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_CATEGORIES_ID, categoryId);
	    contentValues.put(Variables.COLUMN_CATEGORIES_NAME, name);
	    db.insert(Variables.TABLE_CATEGORIES, null, contentValues);
	    return true;
	}
	
	public boolean insertChoices( int id, int wordId, String content){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_CHOICES_ID, id);
	    contentValues.put(Variables.COLUMN_CHOICES_WORD_ID, wordId);
	    contentValues.put(Variables.COLUMN_CHOICES_CONTENT, content);
	    db.insert(Variables.TABLE_CHOICES, null, contentValues);
	    return true;
	}
	
	public boolean insertLessons( int id, int userId, String date, int categoryId ){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_LESSONS_ID, id);
	    contentValues.put(Variables.COLUMN_LESSONS_USER_ID, userId);
	    contentValues.put(Variables.COLUMN_LESSONS_DATE, date);
	    contentValues.put(Variables.COLUMN_LESSONS_CATEGORY_ID, categoryId);
	    db.insert(Variables.TABLE_LESSONS, null, contentValues);
	    return true;
	}
	
	public boolean insertLessonResults( int id, int wordId, int choiceId){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Variables.COLUMN_LESSON_RESULTS_ID, id);
	    contentValues.put(Variables.COLUMN_LESSON_RESULTS_WORD_ID, wordId);
	    contentValues.put(Variables.COLUMN_LESSON_RESULTS_CHOICE_ID, choiceId);
	    db.insert(Variables.TABLE_LESSON_RESULTS, null, contentValues);
	    return true;
	}
	
	public List<User> getAllUser(){
		List<User> result = new ArrayList<User>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.rawQuery( "select * from " + Variables.TABLE_USERS , null );
		res.moveToFirst();
		do{
			User user = new User();
			user.setId(res.getInt(res.getColumnIndex(Variables.COLUMN_USERS_ID)));
			user.setName(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_NAME)));
			user.setAvatar(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_AVATAR)));
			user.setEmail(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_EMAIL)));
			user.setPassword(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_NAME)));
			result.add(user);
		} while(res.moveToNext());
		return result;
	}
	
	public List<Lesson> getLessonListForHome(User user){
		
		List<Lesson> lessonList = new ArrayList<Lesson>();
		SQLiteDatabase db = this.getReadableDatabase();
		int userId = user.getId();
		List<User> userList = new ArrayList<User>();
		
		Cursor res =  db.rawQuery( "select * from " + Variables.TABLE_LESSONS + " where " + Variables.COLUMN_LESSONS_USER_ID + " in " + " ( select " + Variables.COLUMN_FOLLOW_FOLLOWED_ID + " from " + Variables.TABLE_FOLLOW + " where " + Variables.COLUMN_FOLLOW_FOLLOWING_ID + " = " + userId + ")", null );
		System.out.println(res.getCount()+"AAAAAAAAAAA");
		res.moveToFirst();
		
		if(res.getCount() > 0)
		do{
			User _user = new User();
			Lesson lesson = new Lesson();
			Cursor res1 =  db.rawQuery( "select * from " + Variables.TABLE_USERS + " where " + Variables.COLUMN_USERS_ID + " = " + res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_USER_ID)), null );
			res1.moveToFirst();
			if( res1.getCount() > 0)
			do{
				_user.setId(res1.getInt(res1.getColumnIndex(Variables.COLUMN_USERS_ID)));
				_user.setName(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_NAME)));
				_user.setAvatar(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_AVATAR)));
				_user.setEmail(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_EMAIL)));
				_user.setPassword(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_NAME)));
			} while(res1.moveToNext());
			lesson.setUser(_user);
			lesson.setDate(res.getString(res.getColumnIndex(Variables.COLUMN_LESSONS_DATE)));
			lesson.setId(res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_ID)));
			
			Cursor res2 =  db.rawQuery( "select * from " + Variables.TABLE_CATEGORIES + " where " + Variables.COLUMN_CATEGORIES_ID + " = " + res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_CATEGORY_ID)), null );
			res2.moveToFirst();
			Category category = new Category();
			category.setCategoryId(res2.getInt(res2.getColumnIndex(Variables.COLUMN_CATEGORIES_ID)));
			category.setCategoryName(res2.getString(res2.getColumnIndex(Variables.COLUMN_CATEGORIES_NAME)));
			lesson.setCategory(category);
			
			lessonList.add(lesson);
			
		} while(res.moveToNext()); 
		
		
		
		
		
		return lessonList;
	}
	
	
	public List<Lesson> getLessonListForUser(User user){
		
		List<Lesson> lessonList = new ArrayList<Lesson>();
		SQLiteDatabase db = this.getReadableDatabase();
		int userId = user.getId();
		List<User> userList = new ArrayList<User>();
		
		Cursor res =  db.rawQuery( "select * from " + Variables.TABLE_LESSONS + " where " + Variables.COLUMN_LESSONS_USER_ID + " = " + user.getId(), null );
	//	System.out.println(res.getCount()+"AAAAAAAAAAA");
		res.moveToFirst();
		
		if(res.getCount() > 0)
		do{
			Lesson lesson = new Lesson();
			lesson.setUser(user);
			lesson.setDate(res.getString(res.getColumnIndex(Variables.COLUMN_LESSONS_DATE)));
			lesson.setId(res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_ID)));
			
			Cursor res1 = db.rawQuery("select * from " + Variables.TABLE_CATEGORIES + " where " + Variables.COLUMN_CATEGORIES_ID + " = " + res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_CATEGORY_ID)), null);
			res1.moveToFirst();
			Category category = new Category();
			category.setCategoryId(res1.getInt(res1.getColumnIndex(Variables.COLUMN_CATEGORIES_ID)));
			category.setCategoryName(res1.getString(res1.getColumnIndex(Variables.COLUMN_CATEGORIES_NAME)));
			lesson.setCategory(category);
			
			
			/*
			User _user = new User();
			
			Cursor res1 =  db.rawQuery( "select * from " + Variables.TABLE_USERS + " where " + Variables.COLUMN_USERS_ID + " = " + res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_USER_ID)), null );
			res1.moveToFirst();
			if( res1.getCount() > 0)
			do{
				_user.setId(res1.getInt(res1.getColumnIndex(Variables.COLUMN_USERS_ID)));
				_user.setName(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_NAME)));
				_user.setAvatar(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_AVATAR)));
				_user.setEmail(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_EMAIL)));
				_user.setPassword(res1.getString(res1.getColumnIndex(Variables.COLUMN_USERS_NAME)));
			} while(res1.moveToNext());
			
			lesson.setDate(res.getString(res.getColumnIndex(Variables.COLUMN_LESSONS_DATE)));
			lesson.setId(res.getInt(res.getColumnIndex(Variables.COLUMN_LESSONS_ID)));
			*/
			lessonList.add(lesson);
			
		} while(res.moveToNext()); 
		
		
		
		
		
		return lessonList;
	}
	
	
	
	public User getUser(int id){
		User result = new User();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.rawQuery( "select * from " + Variables.TABLE_USERS + " where " + Variables.COLUMN_USERS_ID + " = " + id, null );
		res.moveToFirst();
		if( res.getCount() > 0)
		do{
			User user = new User();
			user.setId(res.getInt(res.getColumnIndex(Variables.COLUMN_USERS_ID)));
			user.setName(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_NAME)));
			user.setAvatar(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_AVATAR)));
			user.setEmail(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_EMAIL)));
			user.setPassword(res.getString(res.getColumnIndex(Variables.COLUMN_USERS_NAME)));
			return user;
		} while(res.moveToNext());
		else return null;
	}
	
	
	/*SUa String --> category sau*/
	public List<String> getCategoryList(){
		List<String> categoryList = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + Variables.TABLE_CATEGORIES, null);
		res.moveToFirst();
		if( res.getCount() > 0)
			do{
				String category = res.getString(res.getColumnIndex(Variables.COLUMN_CATEGORIES_NAME));
				categoryList.add(category);
			} while(res.moveToNext());
		return categoryList;
	}
	
	
	
	public List<Word> getWordList( String category){
		List<Word> wordList = new ArrayList<Word>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + Variables.TABLE_WORDS + " inner join categories on " + Variables.TABLE_WORDS + "." + Variables.COLUMN_WORDS_CATEGORY_ID 
				+ " = " + Variables.TABLE_CATEGORIES + "." + Variables.COLUMN_CATEGORIES_ID + " where " + Variables.TABLE_CATEGORIES+ "." + Variables.COLUMN_CATEGORIES_NAME + " = '" + category +"'", null);
		res.moveToFirst();
		if( res.getCount() > 0)
			do{
				Word word = new Word();
				word.setId(res.getInt(res.getColumnIndex(Variables.COLUMN_WORDS_ID)));
				word.setJpWord(res.getString(res.getColumnIndex(Variables.COLUMN_WORDS_JP_WORD)));
				word.setVnMeaning(res.getString(res.getColumnIndex(Variables.COLUMN_WORDS_VN_MEANING)));
				wordList.add(word);
			} while(res.moveToNext());
		return wordList;
	}
	
}
