package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/SolveMazeServlet")
public class SolveMazeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SolveMazeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		int[][] grid = objectMapper.readValue(request.getParameter("grid"), int[][].class);
		System.out.println(grid[1][1]);
		response.getWriter().write(objectMapper.writeValueAsString(grid));
	}

}
