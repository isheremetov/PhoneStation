package by.gsu.isheremetov.phonestation.servlets.services;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.SubscribeDAO;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet implementation class ServicesEdit
 */
public class ServicesEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicesEdit() {
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
	String[] desubscribe = request.getParameterValues("desubscribe[]");
	String[] subscribe = request.getParameterValues("subscribe[]");
	User user = (User) request.getSession().getAttribute("Authorization");
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	SubscribeDAO subscribeDAO = daoFactory.getSubscribeDAO();
	if (desubscribe != null) {
	    for (String s : desubscribe) {
		try {
		    subscribeDAO.deleteSubscribe(Integer.parseInt(s),
			    user.getId());
		} catch (NumberFormatException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	if (subscribe != null) {
	    for (String s : subscribe) {
		try {
		    subscribeDAO
			    .addSubscribe(Integer.parseInt(s), user.getId());
		} catch (NumberFormatException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	response.sendRedirect("cabinet.jsp");
    }

}
