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
import by.gsu.isheremetov.phonestation.dao.interfaces.ServiceDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.SubscribeDAO;
import by.gsu.isheremetov.phonestation.models.Service;
import by.gsu.isheremetov.phonestation.models.Subscribe;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet Filter implementation class ServicesFilter
 */
public class ServicesFilter implements Filter {

    /**
     * Default constructor.
     */
    public ServicesFilter() {
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
		// TODO Auto-generated method stub
		// place your code here
	    	HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (httpRequest.getSession().getAttribute("Authorization") == null) {
		    httpResponse.sendRedirect("index.jsp");
		} else {
		    User user = (User) httpRequest.getSession().getAttribute(
			    "Authorization");
		    DAOFactory mysqlDaoFactory = DAOFactory
			    .getDAOFactory(DAOFactory.MYSQL);
		    SubscribeDAO subscribeDAO = mysqlDaoFactory.getSubscribeDAO();
		    ServiceDAO serviceDAO = mysqlDaoFactory.getServiceDAO();
		    try {
			ArrayList<Service> services = serviceDAO.getServiceList();
			ArrayList<Subscribe> subscribes = subscribeDAO
				.getSubscribesForUser(user.getId());
			ArrayList<Service> subscribed = new ArrayList<Service>();
			ArrayList<Service> notSubscribed = new ArrayList<Service>();
			Boolean flag;
			for (int i = 0; i < services.size(); i++) {
			    flag = false;
			    for (int j = 0; j < subscribes.size(); j++) {
				if (services.get(i).getId() == subscribes.get(j)
					.getServiceid()) {
				    flag = true;
				    subscribed.add(services.get(i));
				}
			    }
			    if (!flag) {
				notSubscribed.add(services.get(i));
			    }
			}
			HttpSession session = ((HttpServletRequest) request)
				.getSession();
			session.setAttribute("subscribed", subscribed);
			session.setAttribute("notSubscribed", notSubscribed);
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
