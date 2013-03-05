package by.gsu.isheremetov.phonestation.dao.mysqldao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.dao.MySQLDAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.ServiceDAO;
import by.gsu.isheremetov.phonestation.models.Service;

public class MySQLServiceDAO implements ServiceDAO {

    public ArrayList<Service> getServiceList() throws SQLException {
	Connection connection = MySQLDAOFactory.getConnection();
	Statement statement = connection.createStatement();
	String query = "SELECT * FROM Services";
	ResultSet rs = statement.executeQuery(query);
	ArrayList<Service> serviceList = new ArrayList<Service>();
	while (rs.next()) {
	    serviceList.add(new Service(rs.getInt("id"), rs.getString("name"),
		    rs.getString("description"), rs.getInt("price"), rs
			    .getBoolean("active")));
	}
	rs.close();
	statement.close();
	connection.close();
	return serviceList;
    }

}
