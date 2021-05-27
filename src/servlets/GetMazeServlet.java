package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetMazeServlet")
public class GetMazeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetMazeServlet() {
        super();
    }
    //this method is used to get information about the grid the user inputs from the front end
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("gridWidth"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
