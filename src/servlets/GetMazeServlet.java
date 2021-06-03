package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Grid;

@WebServlet("/GetMazeServlet")
public class GetMazeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetMazeServlet() {
        super();
    }
    //this method is used to get information about the grid the user inputs from the front end
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Grid grid = new Grid();
		ObjectMapper objectMapper = new ObjectMapper();
//		paramNames(request, response);
		grid.setGridWidth(Integer.parseInt(request.getParameter("gridWidth")));
		grid.setGridHeight(Integer.parseInt(request.getParameter("gridHeight")));
		int[][] gridMap = grid.createGrid();
		response.getWriter().write(objectMapper.writeValueAsString(gridMap));
	}
	protected void paramNames(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			System.out.println("Hello: "+req.getParameterNames().toString());
	        Enumeration<String> parameterNames = req.getParameterNames();
	        while (parameterNames.hasMoreElements()) {
	 
	            String paramName = parameterNames.nextElement();
	            System.out.println("ParamName: "+paramName);
	 
	            String[] paramValues = req.getParameterValues(paramName);
	            for (int i = 0; i < paramValues.length; i++) {
	                String paramValue = paramValues[i];
	                System.out.println("ParamValue: "+paramValue);
	            }
	 
	        }
	}

}
