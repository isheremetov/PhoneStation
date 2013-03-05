package by.gsu.isheremetov.phonestation.servlets.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    DAOFactory mysqlDaoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	    UserDAO userDao = mysqlDaoFactory.getUserDAO();
	    String login = request.getParameter("phone");
	    String password = request.getParameter("password");
	    String passwordOneMore = request.getParameter("passwordOneMore");
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    if (!password.equals(passwordOneMore)) {
		response.sendRedirect("index.jsp");
	    }
	    try {
		userDao.addUser(login, password, username, email, User.USERROLE_USER, 1);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    response.sendRedirect("index.jsp");
	}

}
