package servlets;

import java.io.IOException;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    //this method is used to get information about the grid the user inputs from the front end and send a grid back
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Grid grid = new Grid();
        ObjectMapper objectMapper = new ObjectMapper();
        grid.setGridHeight(Integer.parseInt(request.getParameter("gridHeight")));
        grid.setGridWidth(Integer.parseInt(request.getParameter("gridWidth")));
        int[][] gridMap = grid.createGrid();
        response.getWriter().write(objectMapper.writeValueAsString(gridMap));
    }
}
