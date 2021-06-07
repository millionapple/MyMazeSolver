package solver;

public class GridSolver {
	public int[][] solveGrid(int[][] grid){
			boolean hasStartAndEnd = gridHasStartAndEnd(grid);
			return grid;
		}

	public boolean gridHasStartAndEnd(int[][] grid) {
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				if(grid[row][column] == 2 && grid[row][column] == 3) {
					return true;
				}
			}
		}
		return false;
	}
}
