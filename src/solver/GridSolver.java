package solver;

import java.util.ArrayList;
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
		numOfWalls = row - 1 < 0 || grid[row-1][column] == 1 ? numOfWalls+1 : numOfWalls+0;
		numOfWalls = column - 1 < 0 || grid[row][column-1] == 1 ? numOfWalls+1 : numOfWalls+0;
		numOfWalls = row +1 >= grid.length || grid[row+1][column] == 1 ? numOfWalls+1 : numOfWalls+0;
		numOfWalls = column +1 >= grid[row].length || grid[row][column+1] == 1 ?numOfWalls+1 : numOfWalls+0;
		return numOfWalls == 4 ? false : true;
	}
	
	ArrayList<int[]> branches = new ArrayList<>();
	int backToBranchCount = 0;
	int numOfBranches = 0;
	
	public int[][] move(int[][] grid, int[] currentRowAndCol){
		System.out.println(Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "\n[").replace("]]", "]"));
		System.out.println("Branching Paths");
		for(int[] branch : branches) {
			System.out.println(Arrays.toString(branch));
		}
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		if(grid[row][column] == 5) {
			grid[row][column] = 4;
		}
		if(pathBranches(grid, currentRowAndCol)) {
			if(branches.isEmpty()) {
				System.out.println("Is empty and creating a branch");
				addBranches(currentRowAndCol);
			}else if(!branches.isEmpty() && !Arrays.equals(currentRowAndCol, branches.get(numOfBranches-1))) {
				System.out.println("Is not empty and not duplicate, creating a branch");
				addBranches(currentRowAndCol);
			}else {
				System.out.println("not a different branch");
			}
			System.out.println("Path Branches");
			for(int[] branch : branches) {
				System.out.println(Arrays.toString(branch));
			}
		}
		if(row-1 >= 0 && grid[row-1][column] == 0) {
			int[] path = new int[]{row-1, column};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row-1][column] = 5;
				move(grid, currentRowAndCol);
			}else {
				grid[row-1][column] = 6;
				move(grid, resetToPreviousBranch(branches));
			}
		}else if(column+1 < grid[row].length && grid[row][column+1] == 0) {
			int[] path = new int[]{row, column+1};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row][column+1] = 5;
				move(grid, currentRowAndCol);
			}else {
				grid[row][column+1] = 6;
				move(grid, resetToPreviousBranch(branches));
			}
		}else if(row+1 < grid.length && grid[row+1][column] == 0) {
			int[] path = new int[]{row+1, column};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row+1][column] = 5;
				move(grid, currentRowAndCol);
			}else {
				grid[row+1][column] = 6;
				move(grid, resetToPreviousBranch(branches));
			}
		}else if(column-1 >= 0 && grid[row][column-1] == 0) {
			int[] path = new int[]{row, column-1};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row][column-1] = 5;
				move(grid, currentRowAndCol);
			}else {
				grid[row][column-1] = 6;
				move(grid, resetToPreviousBranch(branches));
			}
		}
		return grid;
	}

	public boolean pathContinues(int[][] grid, int[] path){
		int row = path[0];
		int column = path[1];
		int numOfWalls = 0;
		numOfWalls = row - 1 < 0 || grid[row-1][column] == 1 ? numOfWalls+1 : numOfWalls+0;
		numOfWalls = column - 1 < 0 || grid[row][column-1] == 1 ? numOfWalls+1 : numOfWalls+0;
		numOfWalls = row +1 >= grid.length || grid[row+1][column] == 1 ? numOfWalls+1 : numOfWalls+0;
		numOfWalls = column +1 >= grid[row].length || grid[row][column+1] == 1 ?numOfWalls+1 : numOfWalls+0;
		return numOfWalls == 3 ? false : true;
	}
	
	
	public boolean pathBranches(int[][] grid, int[]currentRowAndCol) {
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		int paths = 0;
		paths = row-1 >= 0 && grid[row-1][column] == 0 ? paths+1 : paths;
		paths = column-1 >= 0 && grid[row][column-1] == 0 ? paths+1 : paths;
		paths = row+1 < grid.length && grid[row+1][column] == 0 ? paths+1 : paths;
		paths = column+1 < grid[row].length && grid[row][column+1] == 0 ? paths+1 : paths;
		System.out.println(paths>1);
		return paths > 1;
	}
	public void addBranches(int[] currentRowAndCol) {
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		int[] branch = new int[] {row, column};
		System.out.println("Adding Branch: "+Arrays.toString(branch));
		branches.add(branch);
		numOfBranches += 1;
	}
	
	public int[] resetToPreviousBranch(ArrayList<int[]>branches) {
		System.out.println("To Previous Branch: "+Arrays.toString(branches.get(numOfBranches-1)));
		int currentBranch = numOfBranches-1;
		backToBranchCount += 1;
		System.out.println("backToBranchCount: "+backToBranchCount);
		int[] previousBranch = new int[2];
		previousBranch[0] = branches.get(currentBranch)[0];
		previousBranch[1] = branches.get(currentBranch)[1];
		return previousBranch;
	}
}