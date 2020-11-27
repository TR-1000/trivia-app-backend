package dao;

import java.util.List;

import models.Question;

public interface QuestionDAO {

	List<Question> getAllQuestion();
	public boolean addQuestion(Question question);
    public boolean addIncorrect(String incorrect_answer, Long id);
    public boolean addCorrect(String correct_answer, Long id);
    public String[] getIncorrectAnswers(long question_id);
	public String getCorrectAnswer(long question_id);
        
}
