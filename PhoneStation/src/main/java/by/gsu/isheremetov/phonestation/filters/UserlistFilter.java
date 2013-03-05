package by.gsu.isheremetov.phonestation.filters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.isheremetov.phonestation.dao.DAOFactory;
import by.gsu.isheremetov.phonestation.dao.interfaces.UserDAO;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet Filter implementation class UserlistFilter
 */
public class UserlistFilter implements Filter {

    /**
     * Default constructor.
     */
    public UserlistFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (httpRequest.getSession().getAttribute("Authorization") == null) {
		    httpResponse.sendRedirect("index.jsp");
		} else {
		    DAOFactory daoFactory = DAOFactory
			    .getDAOFactory(DAOFactory.MYSQL);
		    UserDAO userDAO = daoFactory.getUserDAO();
		    try {
			ArrayList<User> userlist = userDAO.getUsersList();
			HttpSession session = ((HttpServletRequest) request)
				.getSession();
			session.setAttribute("userlist", userlist);
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
