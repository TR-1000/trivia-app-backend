package model;

public class Answer {
	String text;
	Boolean correct;
	long question_id;
	
	
	// Getter and Setters
	public long getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}
	//////////////////////
	
	
	@Override
	public String toString() {
		return "Answer [text=" + text + ", correct="
				+ correct + ", question_id=" + question_id
				+ "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getCorrect() {
		return correct;
	}
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	
	
}
