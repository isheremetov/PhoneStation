package by.gsu.isheremetov.phonestation.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.isheremetov.phonestation.models.User;

/**
 * Servlet Filter implementation class IndexFilter
 */
public class IndexFilter implements Filter {

    /**
     * Default constructor.
     */
    public IndexFilter() {
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
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	// TODO Auto-generated method stub
	// place your code here
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	if (httpRequest.getSession().getAttribute("Authorization") != null) {
	    User user = (User) httpRequest.getSession().getAttribute("Authorization");
	    if (user.getRole().equals(User.USERROLE_USER)) {
		httpResponse.sendRedirect("cabinet.jsp");
	    } else if (user.getRole().equals(User.USERROLE_ADMIN)){
		httpResponse.sendRedirect("admin.jsp");
	    } else {
		throw new ServletException("Can not define user role");
	    }
	} else {
	    Cookie[] cookies = httpRequest.getCookies();
	    if (cookies != null) {
		int cookiesLength = cookies.length;
		if (cookiesLength > 0) {
		    Boolean phoneFound = false;
		    Boolean passwordFound = false;
		    for (int i = 0; i < cookiesLength; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equalsIgnoreCase(
				"PhoneStationPhone")) {
			    phoneFound = true;
			}
			if (cookie.getName().equalsIgnoreCase(
				"PhoneStationPassword")) {
			    passwordFound = true;
			}
		    }
		    if (phoneFound && passwordFound) {
			httpResponse.sendRedirect("../PhoneStation/Authorization");
		    }
		}
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
