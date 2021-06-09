package solver;

import java.util.Arrays;

public class GridSolver {
	public int[][] solveGrid(int[][] grid){
			boolean hasStartAndEnd = gridHasStartAndEnd(grid);
			boolean startOrEndNotWalledOff = checkStartOrEndWalledOff(grid);
			if(hasStartAndEnd && startOrEndNotWalledOff) {
				//the idea that I have come up with is go through the array and wherever a 0 is replace that with a 
				// 5 to represent the head of the path then replace that with a 4 when it moves on. starting at start.
				int[] startRowAndCol = findStartOrEnd(grid, 2);
				grid = move(grid, startRowAndCol);
			}
			return grid;
		}

	public boolean gridHasStartAndEnd(int[][] grid) {
		boolean hasStart = false;
		boolean hasEnd = false;
		int[] startRowAndCol = findStartOrEnd(grid, 2);
		int[] endRowAndCol = findStartOrEnd(grid, 3);
		hasStart = startRowAndCol[0] == -1 || startRowAndCol[1] == -1 ? false : true;
		hasEnd = endRowAndCol[0] == -1 || endRowAndCol[1] == -1 ? false : true;
		
		return hasStart == true && hasEnd == true;
	}
	public int[] findStartOrEnd(int[][] grid, int value) {
		int[] rowAndColumn = new int[2];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				if(grid[row][column] == value) {
					rowAndColumn[0] = row;
					rowAndColumn[1] = column;
					return rowAndColumn;
				}else {
					rowAndColumn[0] = -1;
					rowAndColumn[1] = -1;
				}
			}
		}
		return rowAndColumn;
	}
	
	public boolean checkStartOrEndWalledOff(int[][] grid) {
		boolean startPath = true;
		boolean endPath = true;
		int[] startRowAndCol = findStartOrEnd(grid, 2);
		int[] endRowAndCol = findStartOrEnd(grid, 3);
		startPath = isPath(startRowAndCol[0], startRowAndCol[1], grid);
		endPath = isPath(endRowAndCol[0], endRowAndCol[1], grid);
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

	public int[][] move(int[][] grid, int[] currentRowAndCol){
		System.out.println(Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "\n[").replace("]]", "]"));
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		if(grid[row][column] == 5) {
			grid[row][column] = 4;
		}
		if(row-1 >= 0 && grid[row-1][column] == 0) {
			currentRowAndCol[0] = row - 1;
			currentRowAndCol[1] = column;
			grid[row-1][column] = 5;
			move(grid, currentRowAndCol);
		}else if(column+1 < grid[row].length && grid[row][column+1] == 0) {
			currentRowAndCol[0] = row;
			currentRowAndCol[1] = column+1;
			grid[row][column+1] = 5;
			move(grid, currentRowAndCol);
		}else if(row+1 < grid.length && grid[row+1][column] == 0) {
			currentRowAndCol[0] = row+1;
			currentRowAndCol[1] = column;
			grid[row+1][column] = 5;
			move(grid, currentRowAndCol);
		}else if(column-1 >= 0 && grid[row][column-1] == 0) {
			currentRowAndCol[0] = row;
			currentRowAndCol[1] = column-1;
			grid[row][column-1] = 5;
			move(grid, currentRowAndCol);
		}
		return grid;
	}
}