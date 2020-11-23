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

import models.Admin;
import models.Player;
import models.User;
import dao.QuestionDAOImpl;

import models.Question;

public class App {
	
	public static void main(String[] args) {
		QuestionDAOImpl q = new QuestionDAOImpl();
		List<Question> questions = q.getAllQuestion();
		System.out.println(questions);
	}
	
	
}
