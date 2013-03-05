package by.gsu.isheremetov.phonestation.dao.mysqldao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.dao.MySQLDAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;
import by.gsu.isheremetov.phonestation.models.User;

public class MySQLUserDAO implements UserDAO {

    public User getUser(String phone, String password) throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	ResultSet rs = statement
		.executeQuery("SELECT * FROM users WHERE phone = '" + phone
			+ "' AND password = MD5('" + password + "')");
	if (rs.next()) { // пользователь найден
	    User user = new User(rs.getInt("id"), rs.getString("phone"),
		    rs.getString("password"), rs.getString("username"),
		    rs.getString("email"), rs.getInt("purse"),
		    rs.getString("role"), rs.getBoolean("active"));
	    rs.close();
	    statement.close();
	    connection.close();
	    return user;
	}
	rs.close();
	statement.close();
	connection.close();
	return null;
    }

    public void addUser(String phone, String password, String username,
	    String email, String role, int active) throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	synchronized (MySQLUserDAO.class) {
	    statement
		    .executeUpdate("insert into users (phone, password, username, email, role, active) values('"
			    + phone
			    + "', md5('"
			    + password
			    + "'), '"
			    + username
			    + "', '"
			    + email
			    + "','"
			    + role
			    + "',"
			    + active + ");");
	}
	statement.close();
	connection.close();
    }

    public ArrayList<User> getUsersList() throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	ArrayList<User> usersList = new ArrayList<User>();
	ResultSet rs = statement.executeQuery("SELECT * FROM users;");
	while (rs.next()) {
	    User user = new User(rs.getInt("id"), rs.getString("phone"),
		    rs.getString("password"), rs.getString("username"),
		    rs.getString("email"), rs.getFloat("purse"),
		    rs.getString("role"), rs.getBoolean("active"));
	    usersList.add(user);
	}
	rs.close();
	statement.close();
	connection.close();
	return usersList;
    }

    @Override
    public void updateUser(int id, String phone, String password,
	    String username, String email, Number purse, String role,
	    Boolean active) throws SQLException {
	StringBuffer sb = new StringBuffer();
	sb.append("UPDATE users SET ");
	if (phone != null) {
	    sb.append("phone =  ");
	    sb.append(phone);
	    sb.append(", ");
	}
	if (password != null) {
	    sb.append("password =  md5(");
	    sb.append(password);
	    sb.append("), ");
	}
	if (username != null) {
	    sb.append("username =  ");
	    sb.append(username);
	    sb.append(", ");
	}
	if (email != null) {
	    sb.append("email = ");
	    sb.append(email);
	    sb.append(", ");
	}
	if (purse != null) {
	    sb.append("purse = ");
	    sb.append(purse);
	    sb.append(", ");
	}
	if (role != null) {
	    sb.append("role = ");
	    sb.append(role);
	    sb.append(", ");
	}
	if (active != null) {
	    sb.append("active = ");
	    sb.append(active);
	    sb.append(", ");
	}
	sb.replace(sb.length() - 2, sb.length(), " ");
	sb.append("WHERE id = ");
	sb.append(id);
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	synchronized (MySQLUserDAO.class) {
	    statement.executeUpdate(sb.toString());
	}
	statement.close();
	connection.close();
    }

}
