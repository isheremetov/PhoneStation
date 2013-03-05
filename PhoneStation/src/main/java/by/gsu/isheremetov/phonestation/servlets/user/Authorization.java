package by.gsu.isheremetov.phonestation.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet implementation class Authorization
 */
public class Authorization extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	if (request.getParameter("action") != null
		&& request.getParameter("action").equals("logout")) {
	    Cookie cookieLogin = new Cookie("PhoneStationPhone", null);
	    cookieLogin.setMaxAge(0);
	    Cookie cookiePassword = new Cookie("PhoneStationPassword", null);
	    cookiePassword.setMaxAge(0);
	    response.addCookie(cookieLogin);
	    response.addCookie(cookiePassword);
	    HttpSession session = request.getSession();
	    session.setAttribute("Authorization", null);
	    response.sendRedirect("index.jsp");
	} else {
	    Cookie[] cookies = request.getCookies();
	    if (cookies == null) {
		response.sendRedirect("index.jsp");
	    }
	    int cookiesLength = cookies.length;
	    String login = null;
	    String password = null;
	    if (cookiesLength > 0) {
		for (int i = 0; i < cookiesLength; i++) {
		    Cookie cookie = cookies[i];
		    if (cookie.getName().equalsIgnoreCase("PhoneStationPhone")) {
			login = cookie.getValue();
		    }
		    if (cookie.getName().equalsIgnoreCase(
			    "PhoneStationPassword")) {
			password = cookie.getValue();
		    }
		}
	    } else {
		response.sendRedirect("index.jsp");
	    }
	    try {
		DAOFactory mysqlDaoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UserDAO userDao = mysqlDaoFactory.getUserDAO();
		User user = userDao.getUser(login, password);
		if (user != null) { // пользователь найден
		    HttpSession session = request.getSession();
		    session.setAttribute("Authorization", user);
		    response.sendRedirect("index.jsp");
		} else { // пользователь не найден
		    response.sendRedirect("index.jsp");
		}
	    } catch (Exception e) {
		throw new ServletException(e.getMessage());
	    }
	}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	try {
	    DAOFactory mysqlDaoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	    UserDAO userDao = mysqlDaoFactory.getUserDAO();
	    User user = userDao.getUser(request.getParameter("phone"), request.getParameter("password"));
	    if (user != null) { // пользователь найден
		HttpSession session = request.getSession();
		if (request.getParameter("rememberMe") != null) {
		    Cookie cookieLogin = new Cookie("PhoneStationPhone",
			    request.getParameter("phone"));
		    cookieLogin.setMaxAge(Integer.MAX_VALUE);
		    Cookie cookiePassword = new Cookie("PhoneStationPassword",
			    request.getParameter("password"));
		    cookiePassword.setMaxAge(Integer.MAX_VALUE);
		    response.addCookie(cookieLogin);
		    response.addCookie(cookiePassword);
		}
		session.setAttribute("Authorization", user);
		response.sendRedirect("index.jsp");
	    } else { // пользователь не найден
		response.sendRedirect("index.jsp");
	    }
	} catch (Exception e) {
	    throw new ServletException(e.getMessage());
	}
    }
}
