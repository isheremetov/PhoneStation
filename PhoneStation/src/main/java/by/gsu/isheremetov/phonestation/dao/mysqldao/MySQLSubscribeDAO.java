package by.gsu.isheremetov.phonestation.dao.mysqldao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.dao.MySQLDAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.SubscribeDAO;
import by.gsu.isheremetov.phonestation.models.Subscribe;

public class MySQLSubscribeDAO implements SubscribeDAO {

    public ArrayList<Subscribe> getSubscribesForUser(int id)
	    throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	ArrayList<Subscribe> subscribes = new ArrayList<Subscribe>();
	String query = "SELECT * FROM subscribes WHERE userid = " + id;
	ResultSet rs = statement.executeQuery(query);
	while (rs.next()) {
	    subscribes.add(new Subscribe(rs.getInt("id"), rs.getInt("userid"),
		    rs.getInt("serviceid"), rs.getDate("activationDate")));
	}
	return subscribes;
    }

    public int addSubscribe(int serviceId, int userId) throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	int num;
	synchronized (MySQLSubscribeDAO.class) {
	    num = statement
		    .executeUpdate("insert into subscribes (userid, serviceid, activationDate) values("
			    + userId + "," + serviceId + ", now());");
	}
	statement.close();
	connection.close();
	return num;
    }

    public int deleteSubscribe(int serviceId, int userId) throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	int num;
	synchronized (MySQLSubscribeDAO.class) {
	    num = statement
		    .executeUpdate("delete from subscribes where userid="
			    + userId + " and serviceid=" + serviceId);
	}
	statement.close();
	connection.close();
	return num;
    }

}
