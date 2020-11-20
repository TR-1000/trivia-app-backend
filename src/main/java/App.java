import model.Admin;
import model.Player;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
//		File file = new File(".");
//		for(String fileNames : file.list()) System.out.println(fileNames);
		
	
//		// CREATE player and admin
//		Player player = new Player("t-1000", "password");
//		System.out.println(player.toString());
//		
//		User user = new Admin("t-2000", "password");
//		System.out.println(user.toString());
		// New ArrayList
		List<String> answerList = new ArrayList<>();
		
		// JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Trivia_Data.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray questionsList = (JSONArray) obj;
            System.out.println(questionsList);
             
            //Iterate over employee array
            questionsList.forEach( q -> parseQuestionObject( (JSONObject) q ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	
		private static void parseQuestionObject(JSONObject question) {    
			//Get question text
			String question_text = (String) question.get("question");    
			System.out.println(question_text);
         
			//Get incorrect answers
			JSONArray incorrect_answers = (JSONArray) question.get("incorrect");  
			System.out.println(incorrect_answers);
         
			//Get correct answer
			String correct_answer = (String) question.get("correct");    
			System.out.println(correct_answer);
		}
	
	
}
