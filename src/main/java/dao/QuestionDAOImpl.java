package dao;

import java.util.List;

import models.Question;

import java.sql.*;
import java.util.ArrayList;
import utilities.ConnectionUtil;

public class QuestionDAOImpl implements QuestionDAO {
	
	@Override
	public boolean addQuestion(Question question) {
		System.out.println("Adding question to database");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			int index=0;
			
			String sql = "INSERT INTO question(text, id) " + "VALUES(?,?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, question.getQuestion());
			statement.setLong(++index, question.getId());
	
			statement.execute();
			return true;

			}catch (SQLException e) {
				System.out.println(e);
			}
			return false;
		}
        
        
        @Override
	public boolean addIncorrect(String incorrect_answer, Long id) {
		System.out.println("Adding incorrect answer to database");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			int index=0;
			
			String sql = "INSERT INTO incorrect_answer(text, question_id) " + "VALUES(?,?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, incorrect_answer);
			statement.setLong(++index, id);
	
			statement.execute();
			return true;

			}catch (SQLException e) {
				System.out.println(e);
			}
			return false;
		}
        
        @Override
	public boolean addCorrect(String correct_answer, Long id) {
		System.out.println("Adding correct answer to database");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			int index=0;
			
			String sql = "INSERT INTO correct_answer(text, question_id) " + "VALUES(?,?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, correct_answer);
			statement.setLong(++index, id);
	
			statement.execute();
			return true;

			}catch (SQLException e) {
				System.out.println(e);
			}
			return false;
		}
        
        

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
