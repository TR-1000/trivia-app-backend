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
			
			String sql = "SELECT text FROM incorrect_answer WHERE id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			
			statement.setLong(++index, id);
			
			List<String> arrayList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				String inc;
				inc = result.getString("text");
				arrayList.add(inc);
			}
			
			
			
			
	
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
			
			String sql = "SELECT * FROM question;";

			PreparedStatement statement = conn.prepareStatement(sql);

			List<Question> questionList = new ArrayList<>();

			ResultSet result = statement.executeQuery();

			while(result.next()) {
				Question q = new Question();
				q.setId(result.getLong("id"));
				q.setQuestion(result.getString("text"));
				q.setIncorrect(getIncorrectAnswers(result.getLong("id")));
				q.setCorrect(getCorrectAnswer(result.getLong("id")));
				questionList.add(q);

			}

			return questionList;

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	@Override
	public String[] getIncorrectAnswers(long question_id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM incorrect_answer WHERE question_id = '" + question_id + "';";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			List<String> arrayList = new ArrayList<>();

			while(result.next()) {
				arrayList.add(result.getString("text"));
			}
			
			String[] incorrectAnswers =  new String[arrayList.size()];
			
			incorrectAnswers = arrayList.toArray(incorrectAnswers);
			
			return incorrectAnswers;

		} catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	
	
	@Override
	public String getCorrectAnswer(long question_id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM correct_answer WHERE question_id ='" + question_id + "';";

			PreparedStatement statement = conn.prepareStatement(sql);
		
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String correctAnswer = result.getString("text");
				return correctAnswer;
			}
			
			

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	
	
}
