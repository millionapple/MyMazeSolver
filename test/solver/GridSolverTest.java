package solver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GridSolverTest {
	
	@Test
	public void hasNoStartOrEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		boolean expected = false;
		
		boolean result = gridSolver.gridHasStartAndEnd(grid);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void hasStartButNoEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		boolean expected = false;
		
		boolean result = gridSolver.gridHasStartAndEnd(grid);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void hasEndButNoStart() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 3;
		boolean expected = false;
		
		boolean result = gridSolver.gridHasStartAndEnd(grid);
		
		assertEquals(expected, result);
	}
	
	@Test
 	public void StartIsSuroundedByWalls() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		grid[2][2] =3;
		boolean expected = false;
		
	}

}
