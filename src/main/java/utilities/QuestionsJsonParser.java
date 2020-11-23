package utilities;

import models.Question;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;




/*
 * This class is used to parse the JSON data in trivia_data.json to a List of Java objects.
 */

public class QuestionsJsonParser {
	
	List<Question> questions = null;
	
	List<Question> getQuestionsList() {
				
		Logger logger = Logger.getLogger("test");
		
		FileHandler handler = null;
		
		try {
			handler = new FileHandler("mylog.xml");
			logger.addHandler(handler);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Error creating log file", e);
		}
		
		try {
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // convert JSON string to Question object
		    List<Question> questions = Arrays.asList(mapper.readValue(Paths.get("trivia_data.json").toFile(), Question[].class));

		    // print questions
		    for (Question q : questions) {
		    	System.out.println("The question is: " + q.getQuestion());
		    	for (String ans : q.getIncorrect()) {
		    		System.out.println(ans);
		    	}
		    	System.out.println(q.getCorrect());
		    }
		    
		    return questions;
		    
		} catch (Exception e) {
			logger.log(Level.WARNING, "Error testing Question object mapper", e);
		    e.printStackTrace();
		}
		
		return questions;
	}
	
	public static void main(String[] args) {
		
		
		
	}

}
