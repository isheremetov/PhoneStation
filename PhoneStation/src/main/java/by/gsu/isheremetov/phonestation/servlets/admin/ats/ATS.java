package by.gsu.isheremetov.phonestation.servlets.admin.ats;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.isheremetov.phonestation.ats.ATSEmulator;

/**
 * Servlet implementation class ATS
 */
public class ATS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ATSEmulator atsEmulator;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ATS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String action = request.getParameter("action");
	    if (action.equals("start") && atsEmulator == null) {
		ATSEmulator atsEmulator = new ATSEmulator();
		atsEmulator.setDaemon(true);
		atsEmulator.start();
		response.sendRedirect("admin.jsp?page=ats");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
