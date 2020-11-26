package utilities;

import java.util.List;

import dao.QuestionDAOImpl;
import models.Question;



/**
 * This class is used to insert trivia questions from the trivia_data.json into the database. 
 * To use it just uncomment the main method and run the file as a Java application.
 * Or you can seed your database by running the script files in the SQL directory.
 */

public class QuestionsBootstrapper {
    
    static QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();
	
	static QuestionsJsonParser questionsParser = new QuestionsJsonParser();
	static long id = 1;
	
//	public static void main(String[] args) {
//            
//            List<Question> questions = questionsParser.getQuestionsList();
//            
//            
//            questions.forEach(q -> {
//            	q.setId(id);
//            	questionDAOImpl.addQuestion(q);
//                questionDAOImpl.addCorrect(q.getCorrect(), id);
//                for (String inc : q.getIncorrect()) 
//                { 
//                	questionDAOImpl.addIncorrect(inc, id);
//                }
//                id++;
//            }); 
//	}
	
	
	
}
