package by.gsu.isheremetov.phonestation.ats;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.DialogueDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.SubscribeDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;
import by.gsu.isheremetov.phonestation.models.Subscribe;
import by.gsu.isheremetov.phonestation.models.User;

public class ATSEmulator extends Thread {
    private Boolean stopped;

    public ATSEmulator() {
	stopped = false;
    }

    public void run() {
	try {
	    DAOFactory mysqlDaoFactory = DAOFactory
		    .getDAOFactory(DAOFactory.MYSQL);
	    UserDAO userDAO = mysqlDaoFactory.getUserDAO();
	    DialogueDAO dialogueDAO = mysqlDaoFactory.getDialogueDAO();
	    SubscribeDAO subscribeDAO = mysqlDaoFactory.getSubscribeDAO();
	    ArrayList<User> userList = userDAO.getUsersList();
	    Random random = new Random();
	    User caller = null;
	    while (!stopped) {
		while (caller == null || !caller.getActive()
			|| caller.getRole().equals(User.USERROLE_ADMIN)) {
		    caller = userList.get(random.nextInt(userList.size()));
		}
		ArrayList<Subscribe> subscribes = subscribeDAO
			.getSubscribesForUser(caller.getId());
		dialogueDAO.addDialogue(new Date(System.currentTimeMillis()),
			random.nextInt(300), new String("111"), caller.getId(),
			subscribes.get(random.nextInt(subscribes.size()))
				.getServiceid());
		try {
		    sleep(3000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	} catch (ServletException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void setStopped(Boolean stopped) {
	this.stopped = stopped;
    }
}
