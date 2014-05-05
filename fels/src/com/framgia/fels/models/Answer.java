package com.framgia.fels.models;

public class Answer {
	private int choice_id;
	private Word word;
	private String content;
	public Word getWord() {
		return word;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getChoice_id() {
		return choice_id;
	}
	public void setChoice_id(int choice_id) {
		this.choice_id = choice_id;
	}	
}
