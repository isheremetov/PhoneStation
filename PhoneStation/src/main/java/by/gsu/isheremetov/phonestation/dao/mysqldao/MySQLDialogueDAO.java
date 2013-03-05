package by.gsu.isheremetov.phonestation.dao.mysqldao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.dao.MySQLDAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.DialogueDAO;
import by.gsu.isheremetov.phonestation.models.Dialogue;

public class MySQLDialogueDAO implements DialogueDAO {

    public void addDialogue(Date dialogueDate, int duration,
	    String destination, int userId, int serviceId) throws SQLException {
	// TODO Auto-generated method stub
	Connection connection = MySQLDAOFactory.getConnection();
	String query = "insert into dialogues(dialogueDate, duration, destination, userId, serviceId) "
		+ "values (?, ?, ?, ?, ?);";
	PreparedStatement preparedStatement = connection
		.prepareStatement(query);
	preparedStatement.setDate(1, dialogueDate);
	preparedStatement.setInt(2, duration);
	preparedStatement.setString(3, destination);
	preparedStatement.setInt(4, userId);
	preparedStatement.setInt(5, serviceId);
	synchronized (MySQLDialogueDAO.class) {
	    preparedStatement.executeUpdate();
	    preparedStatement.close();
	}
	connection.close();
    }

    public ArrayList<Dialogue> getDialogues(int userId) throws SQLException {
	// TODO Auto-generated method stub
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	String query = "select * from dialogues where userid = " + userId;
	ArrayList<Dialogue> dialogueList = new ArrayList<Dialogue>();
	ResultSet rs = statement.executeQuery(query);
	while (rs.next()) {
	    dialogueList.add(new Dialogue(rs.getInt("id"), rs
		    .getDate("dialogueDate"), rs.getInt("duration"), rs
		    .getString("destination"), rs.getInt("userId"), rs
		    .getInt("serviceid")));
	}
	rs.close();
	statement.close();
	connection.close();
	return dialogueList;
    }

    public Dialogue getDialogue(int dialogueId) throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	String query = "select * from dialogues where id = " + dialogueId;
	ResultSet rs = statement.executeQuery(query);
	Dialogue dialogue = null;
	if (rs.next()) {
	    dialogue = new Dialogue(rs.getInt("id"),
		    rs.getDate("dialogueDate"), rs.getInt("duration"),
		    rs.getString("destination"), rs.getInt("userId"),
		    rs.getInt("serviceid"));
	}
	rs.close();
	statement.close();
	connection.close();
	return dialogue;
    }

    @Override
    public void pay(int userId, int dialogueId) throws SQLException {
	// TODO Auto-generated method stub
	String deleteDialogueQuery = "delete from dialogues where id = ?;";
	String updatePurseQuery = "update users set purse = purse - ((select price from services where id = ?) * ?) where id = ?";
	Connection connection = MySQLDAOFactory.getConnection();
	connection.setAutoCommit(false);
	Savepoint savepoint = connection.setSavepoint();
	PreparedStatement deleteDialogueStatement = connection
		.prepareStatement(deleteDialogueQuery);
	PreparedStatement updatePurseStatement = connection
		.prepareStatement(updatePurseQuery);
	deleteDialogueStatement.setInt(1, dialogueId);
	Dialogue dialogue = getDialogue(dialogueId);
	updatePurseStatement.setInt(1, dialogue.getServiceId());
	updatePurseStatement.setInt(2, dialogue.getDuration());
	updatePurseStatement.setInt(3, userId);
	try {
	    synchronized (MySQLDialogueDAO.class) {
		updatePurseStatement.execute();
		deleteDialogueStatement.execute();
	    }
	} catch (SQLException e) {
	    connection.rollback(savepoint);
	    throw new SQLException(e);
	} finally {
	    connection.setAutoCommit(true);
	    connection.close();
	}
    }

}
