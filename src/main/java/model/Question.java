package model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	long id;
	String text;
	List<Answer> answers = new ArrayList<>();
		
	// Getter and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return text;
	}
	public void setQuestion(String question) {
		this.text = question;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	//////////////////////////
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", text=" + text
				+ ", answers=" + answers + "]";
	}
	
	
	

}
