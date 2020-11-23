package dao;

import java.util.List;

import models.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utilities.ConnectionUtil;

public class QuestionDAOImpl implements QuestionDAO {

	@Override
	public List<Question> getAllQuestion() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM question_json;";

			Statement statement = conn.createStatement();

			List<Question> questionList = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				Question q = new Question();

				
				q.setQuestion(result.getString("question"));
				//q.setIncorrect(result.getArray("incorrect"));
				q.setCorrect(result.getString("correct"));
				
				questionList.add(q);


			}

			return questionList;

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	
	
}
