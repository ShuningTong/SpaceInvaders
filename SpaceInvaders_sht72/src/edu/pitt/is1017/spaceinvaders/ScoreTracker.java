package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class ScoreTracker {
	private User user;
	private int currentScore;
	private int highestScore;
	private String highestScorePlayer;
	private int highestScorePlayerID;
	private String gameID;


	public ScoreTracker(User user){
		this.user = user;
		this.currentScore = 0;
		this.gameID = UUID.randomUUID().toString();
		DbUtilities db = new DbUtilities();
		String sql = "SELECT firstName, lastName, fk_userID, MAX(scoreValue) highest_score FROM finalscores JOIN users ON fk_userID = userID GROUP BY fk_userID ORDER BY highest_score DESC LIMIT 1;";
		ResultSet rs = db.getResultSet(sql);
		try {
			if(rs.next()){
				this.highestScore = rs.getInt("highest_score");
				this.highestScorePlayer = rs.getString("firstName") + " " + rs.getString("lastName");
				this.highestScorePlayerID = rs.getInt("fk_userID");
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void recordScore(int point){
		this.currentScore += point;
		DbUtilities db = new DbUtilities();
		String sql = "INSERT INTO runningscores (gameID, scoreType, scoreValue, fk_userID, dateTimeEntered) VALUES ('" + this.gameID + "', " + true + ", " + this.currentScore + ", " + this.user.getUserID() + ", NOW());";
		db.executeQuery(sql);
	}
	public void recordFinalScore(){
		DbUtilities db = new DbUtilities();
		String sql = "INSERT INTO finalscores (gameID, scoreValue, fk_userID, dateTimeEntered) VALUES ('" + this.gameID + "', " + this.currentScore + ", " + this.user.getUserID() + ", NOW());";
		db.executeQuery(sql);
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public String getHighestScorePlayer() {
		return highestScorePlayer;
	}
	public int getHighestScorePlayerID() {
		return highestScorePlayerID;
	}
	public static void main(String[] args){
		ScoreTracker st = new ScoreTracker(new User(70));
		st.recordFinalScore();
	}



}
