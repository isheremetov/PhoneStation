package by.gsu.isheremetov.phonestation.filters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import by.gsu.isheremetov.phonestation.dao.interfaces.DialogueDAO;
import by.gsu.isheremetov.phonestation.dao.interfaces.ServiceDAO;
import by.gsu.isheremetov.phonestation.models.Dialogue;
import by.gsu.isheremetov.phonestation.models.Service;
import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet Filter implementation class DialoguesFilter
 */
public class DialoguesFilter implements Filter {

    /**
     * Default constructor.
     */
    public DialoguesFilter() {
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
		    DAOFactory daoFactory = DAOFactory
			    .getDAOFactory(DAOFactory.MYSQL);
		    DialogueDAO dialogueDAO = daoFactory.getDialogueDAO();
		    ServiceDAO serviceDAO = daoFactory.getServiceDAO();
		    try {
			ArrayList<Dialogue> dialogueList = dialogueDAO.getDialogues(user.getId());
			ArrayList<Service> serviceList = serviceDAO.getServiceList();
			Map<Integer, Service> services = new HashMap<Integer, Service>();
			for (int i = 0; i < serviceList.size(); i++) {
			    services.put(serviceList.get(i).getId(), serviceList.get(i));
			}
			ArrayList<Number> dialoguePaymentList = new ArrayList<Number>();
			for (int i = 0; i < dialogueList.size(); i ++) {
			    Dialogue dialogue = dialogueList.get(i);
			    dialoguePaymentList.add(new Integer(dialogue.getDuration() * services.get(dialogue.getServiceId()).getPrice()));
			}
			HttpSession session = ((HttpServletRequest) request)
				.getSession();
			session.setAttribute("dialogue", dialogueList);
			session.setAttribute("dialoguePayment", dialoguePaymentList);
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
