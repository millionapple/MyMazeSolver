package solver;

import java.util.ArrayList;
import java.util.Arrays;

public class GridSolver {
	final int NOT_VALID = -1;
	final int PATH = 0;
	final int WALL = 1;
	final int START = 2;
	final int END = 3;
	final int WALKED_PATH = 4;
	final int HEAD = 5;
	final int DEAD_END = 6;
	
 	public int[][] solveGrid(int[][] grid){
			boolean hasStartAndEnd = gridHasStartAndEnd(grid);
			boolean startOrEndNotWalledOff = checkStartOrEndWalledOff(grid);
			if(hasStartAndEnd && startOrEndNotWalledOff) {
				int[] startRowAndCol = findStartOrEnd(grid, START);
				grid = move(grid, startRowAndCol);
			}
			return grid;
		}

	public boolean gridHasStartAndEnd(int[][] grid) {
		int[] startRowAndCol = findStartOrEnd(grid, START);
		int[] endRowAndCol = findStartOrEnd(grid, END);
		boolean hasStart = startRowAndCol[0] != NOT_VALID && startRowAndCol[1] != NOT_VALID;
		boolean hasEnd = endRowAndCol[0] != NOT_VALID && endRowAndCol[1] != NOT_VALID;
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
					rowAndColumn[0] = NOT_VALID;
					rowAndColumn[1] = NOT_VALID;
				}
			}
		}
		return rowAndColumn;
	}
	
	public boolean checkStartOrEndWalledOff(int[][] grid) {
		int[] startRowAndCol = findStartOrEnd(grid, START);
		int[] endRowAndCol = findStartOrEnd(grid, END);
		boolean startPath = isPath(startRowAndCol, grid);
		boolean endPath = isPath(endRowAndCol, grid);
		return startPath == true && endPath == true;
	}
	public boolean isPath(int[] rowAndCol, int[][] grid) {
		int row = rowAndCol[0];
		int column = rowAndCol[1];
		int numOfWalls = 0;
		numOfWalls = row - 1 < 0 || grid[row-1][column] == WALL ? numOfWalls+1 : numOfWalls;
		numOfWalls = column - 1 < 0 || grid[row][column-1] == WALL ? numOfWalls+1 : numOfWalls;
		numOfWalls = row +1 >= grid.length || grid[row+1][column] == WALL ? numOfWalls+1 : numOfWalls;
		numOfWalls = column +1 >= grid[row].length || grid[row][column+1] == WALL ?numOfWalls+1 : numOfWalls;
		return numOfWalls < 4;
	}
	
	ArrayList<int[]> branches = new ArrayList<>();
	int deadBranches = 0;
	int numOfBranches = 0;
	
	public int[][] move(int[][] grid, int[] currentRowAndCol){
		System.out.println(Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "\n[").replace("]]", "]"));
		System.out.println("Branching Paths: ");
		for(int[] branch : branches) {
			System.out.println(Arrays.toString(branch));
		}
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		if(grid[row][column] == HEAD) {
			grid[row][column] = WALKED_PATH;
		}
		if(foundEnd(grid, currentRowAndCol)) {
			System.out.println("End has been found");
			return grid;
		}else if(pathBranches(grid, currentRowAndCol)) {
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
		}else if(walledOff(grid, currentRowAndCol)) {
			System.out.println("Branch is fully mapped out and cannot be navigated. Back to Previous branch.");
			deadBranches+=1;
			numOfBranches = numOfBranches - deadBranches;
			branches.remove(numOfBranches);
			move(grid, resetToPreviousBranch(branches));
		}
		if(row-1 >= 0 && grid[row-1][column] == PATH) {
			int[] path = new int[]{row-1, column};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row-1][column] = HEAD;
				move(grid, currentRowAndCol);
			}else {
				grid[row-1][column] = DEAD_END;
				move(grid, resetToPreviousBranch(branches));
			}
		}else if(column+1 < grid[row].length && grid[row][column+1] == PATH) {
			int[] path = new int[]{row, column+1};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row][column+1] = HEAD;
				move(grid, currentRowAndCol);
			}else {
				grid[row][column+1] = DEAD_END;
				move(grid, resetToPreviousBranch(branches));
			}
		}else if(row+1 < grid.length && grid[row+1][column] == PATH) {
			int[] path = new int[]{row+1, column};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row+1][column] = HEAD;
				move(grid, currentRowAndCol);
			}else {
				grid[row+1][column] = DEAD_END;
				move(grid, resetToPreviousBranch(branches));
			}
		}else if(column-1 >= 0 && grid[row][column-1] == PATH) {
			int[] path = new int[]{row, column-1};
			if(pathContinues(grid, path)) {
				currentRowAndCol[0] = path[0];
				currentRowAndCol[1] = path[1];
				grid[row][column-1] = HEAD;
				move(grid, currentRowAndCol);
			}else {
				grid[row][column-1] = DEAD_END;
				move(grid, resetToPreviousBranch(branches));
			}
		}
		return grid;
	}

	public boolean pathContinues(int[][] grid, int[] path){
		int row = path[0];
		int column = path[1];
		int numOfWalls = 0;
		numOfWalls = row - 1 < 0 || grid[row-1][column] != PATH & grid[row-1][column] != END ? numOfWalls+1 : numOfWalls;
		numOfWalls = column - 1 < 0 || grid[row][column-1] != PATH & grid[row][column-1] != END ? numOfWalls+1 : numOfWalls;
		numOfWalls = row +1 >= grid.length || grid[row+1][column] != PATH & grid[row+1][column] != END ? numOfWalls+1 : numOfWalls;
		numOfWalls = column +1 >= grid[row].length || grid[row][column+1] != PATH & grid[row][column+1] != END ?numOfWalls+1 : numOfWalls;
		System.out.println("Path Continues: "+ (numOfWalls!=3));
		return numOfWalls < 4;
	}
	public boolean walledOff(int[][] grid, int[] currentRowAndCol) {
		System.out.println("doing walled off function");
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		int numOfWalls = 0;
		numOfWalls = row - 1 < 0 || grid[row-1][column] != PATH & grid[row-1][column] != END ? numOfWalls+1 : numOfWalls;
		numOfWalls = column - 1 < 0 || grid[row][column-1] != PATH & grid[row][column-1] != END ? numOfWalls+1 : numOfWalls;
		numOfWalls = row +1 >= grid.length || grid[row+1][column] != PATH & grid[row+1][column] != END ? numOfWalls+1 : numOfWalls;
		numOfWalls = column +1 >= grid[row].length || grid[row][column+1] != PATH & grid[row][column+1] != END ?numOfWalls+1 : numOfWalls;
		return numOfWalls > 3;
	}
	
	public boolean foundEnd(int[][] grid, int[] currentRowAndCol) {
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		int end = 0;
		end = row-1 >= 0 && grid[row-1][column] == END ? end+1 : end;
		end = column-1 >= 0 && grid[row][column-1] == END ? end+1 : end;
		end = row+1 < grid.length && grid[row+1][column] == END ? end+1 : end;
		end = column+1 < grid[row].length && grid[row][column+1] == END ? end+1 : end;
		System.out.println(end>0);
		return end > 0;
	}
	public boolean pathBranches(int[][] grid, int[]currentRowAndCol) {
		int row = currentRowAndCol[0];
		int column = currentRowAndCol[1];
		int paths = 0;
		paths = row-1 >= 0 && grid[row-1][column] == PATH ? paths+1 : paths;
		paths = column-1 >= 0 && grid[row][column-1] == PATH ? paths+1 : paths;
		paths = row+1 < grid.length && grid[row+1][column] == PATH ? paths+1 : paths;
		paths = column+1 < grid[row].length && grid[row][column+1] == PATH ? paths+1 : paths;
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
		int currentBranch = numOfBranches-1;
		int[] previousBranch = new int[2];
		try {
		System.out.println("To Previous Branch: "+Arrays.toString(branches.get(numOfBranches-1)));
		previousBranch[0] = branches.get(currentBranch)[0];
		previousBranch[1] = branches.get(currentBranch)[1];
		return previousBranch;
		}catch(ArrayIndexOutOfBoundsException exception) {
			System.out.println("The Maze cannot be solved it has tried all paths: "+exception);
		}
		return previousBranch;
	}
}