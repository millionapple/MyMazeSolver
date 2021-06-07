package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import solver.GridSolver;

@WebServlet("/SolveMazeServlet")
public class SolveMazeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SolveMazeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		GridSolver solver = new GridSolver();
		int[][] grid = objectMapper.readValue(request.getParameter("grid"), int[][].class);
		int[][] solvedGrid = solver.solveGrid(grid);
		response.getWriter().write(objectMapper.writeValueAsString(solvedGrid));
	}

}
