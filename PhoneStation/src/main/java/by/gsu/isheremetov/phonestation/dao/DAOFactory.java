package by.gsu.isheremetov.phonestation.dao;

import javax.servlet.ServletException;

import by.gsu.isheremetov.phonestation.dao.interfaces.DialogueDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.ServiceDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.SubscribeDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;

public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int MYSQL = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract UserDAO getUserDAO();
    public abstract ServiceDAO getServiceDAO();
    public abstract SubscribeDAO getSubscribeDAO();
    public abstract DialogueDAO getDialogueDAO();

    public static DAOFactory getDAOFactory(int whichFactory) throws ServletException {
	switch (whichFactory) {
	case MYSQL:
	    return new MySQLDAOFactory();
	default:
	    return null;
	}
    }
}
