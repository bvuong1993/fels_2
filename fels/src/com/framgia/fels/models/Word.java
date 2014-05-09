package com.framgia.fels.models;

public class Word {
	private int id;
	private String jpWord;
	private String vnMeaning;
	/*
	private int resultId;
	private String sound;
	private int categoryId;
	*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJpWord() {
		return jpWord;
	}
	public void setJpWord(String jpWord) {
		this.jpWord = jpWord;
	}
	public String getVnMeaning() {
		return vnMeaning;
	}
	public void setVnMeaning(String vnMeaning) {
		this.vnMeaning = vnMeaning;
	}
	/*
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}*/
	
}
