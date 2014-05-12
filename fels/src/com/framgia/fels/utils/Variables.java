package com.framgia.fels.utils;

public class Variables {
	/**
	 * Database name
	 */
	public static final String DATABASE_NAME = "fels.db";
	
	/**
	 * Tables Names
	 */
	public static final String TABLE_USERS = "Users";
	public static final String TABLE_FOLLOW = "Follow";
	public static final String TABLE_WORDS = "Words";
	public static final String TABLE_CATEGORIES = "Categories";
	public static final String TABLE_CHOICES = "Choices";
	public static final String TABLE_LESSONS = "Lessons";
	public static final String TABLE_LESSON_RESULTS = "Lesson_Results";
	
	/**
	 * Column Names
	 */
	//table users
	public static final String COLUMN_USERS_ID = "id";
	public static final String COLUMN_USERS_NAME = "name";
	public static final String COLUMN_USERS_EMAIL = "email";
	public static final String COLUMN_USERS_PASSWORD = "password";
	public static final String COLUMN_USERS_AVATAR = "avatar";
	
	//table follow
	public static final String COLUMN_FOLLOW_FOLLOWING_ID = "following_id";
	public static final String COLUMN_FOLLOW_FOLLOWED_ID = "followed_id";
	
	//table words
	public static final String COLUMN_WORDS_ID = "id";
	public static final String COLUMN_WORDS_JP_WORD = "jp_word";
	public static final String COLUMN_WORDS_VN_MEANING = "vn_meaning";
	public static final String COLUMN_WORDS_RIGHT_CHOICE_ID = "result_id";
	public static final String COLUMN_WORDS_SOUND = "sound";
	public static final String COLUMN_WORDS_CATEGORY_ID = "category_id";
	
	//table categories
	public static final String COLUMN_CATEGORIES_ID = "id";
	public static final String COLUMN_CATEGORIES_NAME = "name";
	
	//table choices
	public static final String COLUMN_CHOICES_ID = "id";
	public static final String COLUMN_CHOICES_WORD_ID = "word_id";
	public static final String COLUMN_CHOICES_CONTENT = "content";
	
	//table lessons
	public static final String COLUMN_LESSONS_ID = "id";
	public static final String COLUMN_LESSONS_USER_ID = "user_id";
	public static final String COLUMN_LESSONS_DATE = "date";
	public static final String COLUMN_LESSONS_CATEGORY_ID = "categoryId";
	
	//table lesson_results
	public static final String COLUMN_LESSON_RESULTS_ID = "id";
	public static final String COLUMN_LESSON_RESULTS_WORD_ID = "word_id";
	public static final String COLUMN_LESSON_RESULTS_CHOICE_ID = "choiceId";
	
}
