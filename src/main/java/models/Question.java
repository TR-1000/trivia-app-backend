package models;

import java.util.Arrays;


public class Question {
	
	private long id;
	private String question;
	private String[] incorrect;
	private String correct;
	
	public Question() {}
	
	public Question(String question, String[] incorrect, String correct) {
		this.question = question;
		this.incorrect = incorrect;
		this.correct = correct;
		
	}

	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getIncorrect() {
		return incorrect;
	}

	public void setIncorrect(String[] array) {
		this.incorrect = array;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question="
				+ question + ", incorrect=" + Arrays
						.toString(incorrect) + ", correct="
				+ correct + "]";
	}

	

	
		
	
	
}
