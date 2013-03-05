package by.gsu.isheremetov.phonestation.servlets.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.DialogueDAO;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet implementation class Payment
 */
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	    DialogueDAO dialogueDAO = daoFactory.getDialogueDAO();
	    User user = (User) request.getSession().getAttribute("Authorization");
	    String[] pay = request.getParameterValues("pay[]");
	    if (pay != null) {
		    for (String s : pay) {
			try {
			    dialogueDAO.pay(user.getId(), Integer.parseInt(s));
			} catch (NumberFormatException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (SQLException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    }
		}
	    response.sendRedirect("cabinet.jsp?page=dialogues");
	}

}
