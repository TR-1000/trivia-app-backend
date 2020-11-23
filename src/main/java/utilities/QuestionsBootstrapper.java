package utilities;

import models.Question;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import dao.QuestionDAOImpl;

/*
 * This class is used to insert the trivia questions from the trivia_data.json into the database.
 */

public class QuestionsBootstrapper {
	
	//List<Question> questionsList = new ArrayList<>();
	
	public static void main(String[] args) {
		
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
		    questions.forEach(System.out::println);

		} catch (Exception e) {
			logger.log(Level.WARNING, "Error testing Question object mapper", e);
		    e.printStackTrace();
		}
		
	}

}
