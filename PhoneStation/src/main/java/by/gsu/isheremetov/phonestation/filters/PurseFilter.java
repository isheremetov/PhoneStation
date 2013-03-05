package by.gsu.isheremetov.phonestation.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PurseFilter
 */
public class PurseFilter implements Filter {

    /**
     * Default constructor.
     */
    public PurseFilter() {
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
		    /*
		    User user = (User) httpRequest.getSession().getAttribute(
			    "Authorization");
		    DAOFactory daoFactory = DAOFactory
			    .getDAOFactory(DAOFactory.MYSQL);
		    DialogueDAO dialogueDAO = daoFactory.getDialogueDAO();
		    try {
			ArrayList<Dialogue> dialogs = dialogueDAO.getDialogues(user.getId());
			int total;
			for(int i = 0; i < dialogs.size(); i++) {
			    total += dialogs.get(i).
			}
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    */
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
