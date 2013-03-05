package by.gsu.isheremetov.phonestation.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import by.gsu.isheremetov.phonestation.dao.interfaces.DialogueDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.ServiceDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.SubscribeDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;
import by.gsu.isheremetov.phonestation.dao.mysqldao.MySQLDialogueDAO;
import by.gsu.isheremetov.phonestation.dao.mysqldao.MySQLServiceDAO;
import by.gsu.isheremetov.phonestation.dao.mysqldao.MySQLSubscribeDAO;
import by.gsu.isheremetov.phonestation.dao.mysqldao.MySQLUserDAO;

public class MySQLDAOFactory extends DAOFactory {
    private static DataSource pool;

    public MySQLDAOFactory() throws ServletException {
	if (pool == null) {
	    Context context = null;
	    try {
		context = (Context) new InitialContext()
			.lookup("java:/comp/env");
		pool = (DataSource) context.lookup("jdbc/PhoneStation");
		if (pool == null) {
		    throw new ServletException(
			    "jdbc/PhoneStation is incorrect DataSource");
		}
	    } catch (NamingException e) {
		throw new ServletException(e.getMessage());
	    }
	}
    }

    public synchronized static Connection getConnection() throws SQLException {
	return pool.getConnection();
    }

    public UserDAO getUserDAO() {
	return new MySQLUserDAO();
    }

    public ServiceDAO getServiceDAO() {
	return new MySQLServiceDAO();
    }

    public SubscribeDAO getSubscribeDAO() {
	return new MySQLSubscribeDAO();
    }

    @Override
    public DialogueDAO getDialogueDAO() {
	return new MySQLDialogueDAO();
    }
}
