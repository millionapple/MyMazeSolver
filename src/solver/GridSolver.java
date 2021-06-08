package solver;

public class GridSolver {
	public int[][] solveGrid(int[][] grid){
			boolean hasStartAndEnd = gridHasStartAndEnd(grid);
			return grid;
		}

	public boolean gridHasStartAndEnd(int[][] grid) {
		boolean hasStart = false;
		boolean hasEnd = false;
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				if(grid[row][column] == 2) {
					hasStart = true;
				}else if(grid[row][column] == 3) {
					hasEnd = true;
				}
			}
		}
		return hasStart == true && hasEnd == true;
	}
	public boolean checkAllNeighbors(int[][] grid) {
		boolean startPath = true;
		boolean endPath = true;
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				if(grid[row][column] == 2) {
					startPath = isPath(row, column, grid);
				}else if(grid[row][column] == 3) {
					endPath = isPath(row, column, grid);
				}
			}
		}
		return startPath == false || endPath == false ? false : true;
	}
	public boolean isPath(int row, int column, int[][] grid) {
		int numOfWalls = 0;
		numOfWalls = row - 1 < 0 || grid[row-1][column] == 1 ? numOfWalls+1 : +0;
		numOfWalls = column - 1 < 0 || grid[row][column-1] == 1 ? numOfWalls+1 : +0;
		numOfWalls = row +1 >= grid.length || grid[row+1][column] == 1 ? numOfWalls+1 : +0;
		numOfWalls = column +1 >= grid[row].length || grid[row][column+1] == 1 ?numOfWalls+1 : +0;
		return numOfWalls == 4 ? false : true;
	}
}