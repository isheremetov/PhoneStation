package by.gsu.isheremetov.phonestation.servlets.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;

/**
 * Servlet implementation class UserlistEdit
 */
public class UserlistEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserlistEdit() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String[] activate = request.getParameterValues("activate[]");
	String[] deactivate = request.getParameterValues("deactivate[]");
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	UserDAO userDAO = daoFactory.getUserDAO();
	if (activate != null) {
	    for (String s : activate) {
		try {
		    userDAO.updateUser(Integer.parseInt(s), null, null, null, null, null, null, true);
		} catch (NumberFormatException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	if (deactivate != null) {
	    for (String s : deactivate) {
		try {
		    userDAO.updateUser(Integer.parseInt(s), null, null, null, null, null, null, false);
		} catch (NumberFormatException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	response.sendRedirect("admin.jsp?page=userlist");
    }

}
