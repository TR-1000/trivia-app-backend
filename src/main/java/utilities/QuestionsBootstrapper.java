package utilities;

import java.util.List;

import models.Question;

/**
 * This class is used to insert trivia questions from the trivia_data.json into the database.
 */

public class QuestionsBootstrapper {
	
	static QuestionsJsonParser questionsParser = new QuestionsJsonParser();
	
	public static void main(String[] args) {
		List<Question> questions = questionsParser.getQuestionsList();
		
		// TODO: Add questions and answers to the database
	}
	
}
