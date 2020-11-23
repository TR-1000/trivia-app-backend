package models;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
	String question;
	String[] incorrect;
	String correct;
	
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

	@Override
	public String toString() {
		return "Question [question=" + question
				+ ", incorrect=" + Arrays.toString(
						incorrect) + ", correct=" + correct
				+ "]";
	}

	
		
	
	
}
