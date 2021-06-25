package solver;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	public void hasBothStartAndEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[1][1] = 2;
		grid[2][2] = 3;
		boolean expected = true;
		
		boolean result = gridSolver.gridHasStartAndEnd(grid);
		
		assertEquals(expected, result);
	}

	@Test
	public void testFindStart() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[0][1] = 1;
		grid[1][0] = 1;
		grid[2][1] = 1;
		grid[1][2] = 1;
		grid[1][1] = 2;
		int[] expected = new int[] {1,1};
		
		int[] result = gridSolver.findStartOrEnd(grid, 2);
		
		assertArrayEquals(expected, result);
	}
	@Test
	public void testFindEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[0][1] = 1;
		grid[1][0] = 1;
		grid[2][1] = 1;
		grid[1][2] = 1;
		grid[1][1] = 3;
		int[] expected = new int[] {1,1};
		
		int[] result = gridSolver.findStartOrEnd(grid, 3);
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void startSurroundedByWalls() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[0][1] = 1;
		grid[1][0] = 1;
		grid[2][1] = 1;
		grid[1][2] = 1;
		grid[1][1] = 2;
		grid[3][3] = 3;
		boolean expected = false;
		
		boolean result = gridSolver.checkStartOrEndWalledOff(grid);
		
		assertEquals(expected, result);
	}
	@Test 
	public void endSurroundedByWalls() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[3][3] = 2;
		grid[0][1] = 1;
		grid[1][0] = 1;
		grid[2][1] = 1;
		grid[1][2] = 1;
		grid[1][1] = 3;
		boolean expected = false;
		
		boolean result = gridSolver.checkStartOrEndWalledOff(grid);
		
		assertEquals(expected, result);
	}
	@Test
	public void startInCornerSurroundedByWalls() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[1][0] = 1;
		grid[0][1] = 1;
		grid[0][0] = 2;
		grid[3][3] = 3;
		boolean expected = false;
		
		boolean result = gridSolver.checkStartOrEndWalledOff(grid);
		
		assertEquals(expected, result);
	}
	@Test
	public void notSurroundedByWalls() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[1][1] = 2;
		grid[2][2] = 3;
		boolean expected = true;
		
		boolean result = gridSolver.checkStartOrEndWalledOff(grid);
		
		assertEquals(expected, result);
	}
	@Test
	public void endNotSurroundedStartIs() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[0][0] = 2;
		grid[1][0] = 1;
		grid[0][1] = 1;
		grid[2][2] = 3;
		boolean expected = false;
		
		boolean result = gridSolver.checkStartOrEndWalledOff(grid);
		
		assertEquals(expected, result);
	}
	@Test
	public void startNotSurroundedEndIs() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		grid[1][1] = 2;
		grid[3][3] = 3;
		grid[2][3] = 1;
		grid[3][2] = 1;
		boolean expected = false;
		
		boolean result = gridSolver.checkStartOrEndWalledOff(grid);
		
		assertEquals(expected, result);
	}

	@Test
	public void testIsPathWalledOff() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		boolean expected = false;
		
		boolean result = gridSolver.isPath(1, 1, grid);
		
		assertEquals(expected, result);
	}
	@Test
	public void testIsPathClear() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 0;
			}
		}
		grid[1][1] = 2;
		boolean expected = true;
		
		boolean result = gridSolver.isPath(1, 1, grid);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testingMoveCanMove() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		int[] start = new int[]{0, 0};
		grid[0][0] = 2;
		grid[2][3] = 3;
		grid[1][0] = 1;
		grid[1][1] = 1;
		grid[1][2] = 1;
		grid[2][2] = 1;
		int[][] expected = new int[4][4];
		expected[0][0] = 2;
		expected[2][3] = 3;
		expected[1][0] = 1;
		expected[1][1] = 1;
		expected[1][2] = 1;
		expected[2][2] = 1;
		expected[0][1] = 4;
		expected[0][2] = 4;
		expected[0][3] = 4;
		expected[1][3] = 4;
		
		System.out.println("Testing Move can Move");
		int[][] result = gridSolver.move(grid, start);
		
		assertArrayEquals(expected, result);
	}
	@Test
	public void unsolvableMaze() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		int[] start = new int[]{0, 0};
		grid[0][0] = 2;
		grid[2][3] = 3;
		grid[1][0] = 1;
		grid[1][1] = 1;
		grid[1][2] = 1;
		grid[2][2] = 1;
		grid[0][3] = 1;
		int[][] expected = new int[4][4];
		expected[0][0] = 2;
		expected[2][3] = 3;
		expected[1][0] = 1;
		expected[1][1] = 1;
		expected[1][2] = 1;
		expected[2][2] = 1;
		expected[0][1] = 4;
		expected[0][2] = 4;
		expected[0][3] = 1;
		expected[1][3] = 0;
		
		System.out.println("Unsolvable Maze");
		int[][] result = gridSolver.move(grid, start);
		
		assertArrayEquals(expected, result);
	}
	@Test 
	public void differentMaze() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[5][5];
		int[] start = new int[]{1, 0};
		for(int i=0; i<grid[0].length; i++) {
			grid[0][i] =1;
		}
		grid[1][0] = 2;
		grid[1][1] = 1;
		grid[2][1] = 1;
		grid[2][3] = 1;
		grid[3][1] = 1;
		grid[3][3] = 1;
		grid[4][3] = 1;
		grid[4][4] = 3;
		int[][] expected = new int[5][5];
		for(int row = 0; row < expected.length; row++) {
			for(int column = 0; column < expected[row].length; column++) {
				expected[row][column] = 4;
			}
		}
		for(int i=0; i<expected[0].length; i++) {
			expected[0][i] =1;
		}
		expected[1][0] = 2;
		expected[1][1] = 1;
		expected[2][1] = 1;
		expected[2][3] = 1;
		expected[3][1] = 1;
		expected[3][3] = 1;
		expected[4][3] = 1;
		expected[4][4] = 3;
		
		System.out.println("Different Maze Test");
		int[][] result = gridSolver.move(grid, start);
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testNextToWallNoPath() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		int [] path = new int[] {1,1};
		boolean expected = false;
		
		boolean result = gridSolver.pathContinues(grid, path);
		
		assertEquals(expected, result);
	}
	@Test
	public void testPathDoesContinue() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		grid[1][2] = 0;
		grid[2][2] = 0;
		int[] path = new int[] {1, 2};
		boolean expected = true;
		
		boolean result = gridSolver.pathContinues(grid, path);
		
		assertEquals(expected, result);
	}

	@Test
	public void testWalledOff() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = true;
		
		boolean result = gridSolver.walledOff(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}
	@Test
	public void testWalledOffBySide() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[0][0] = 2;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = true;
		
		boolean result = gridSolver.walledOff(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}
	@Test
	public void testNotWalledOff() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 0;
			}
		}
		grid[1][1] = 2;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = false;
		
		boolean result = gridSolver.walledOff(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}

	@Test
	public void testFoundEndIsNextToEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 4;
		grid[1][0] = 3;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = true;
		
		boolean result = gridSolver.foundEnd(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}
	@Test
	public void testFoundEndIsDiagonalToEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 4;
		grid[2][2] = 3;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = false;
		
		boolean result = gridSolver.foundEnd(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}
	@Test
	public void testFoundEndNotNearEnd() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 4;
		grid[3][3] = 3;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = false;
		
		boolean result = gridSolver.foundEnd(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}

	@Test
	public void testPathBranches() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		grid[0][1] = 0;
		grid[1][0] = 0;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = true;
		
		boolean result = gridSolver.pathBranches(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}
	@Test
	public void testIsPathButDoesNotBranch() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		grid[0][1] = 0;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = false;
		
		boolean result = gridSolver.pathBranches(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}
	@Test
	public void testNoBranch() {
		GridSolver gridSolver = new GridSolver();
		int[][] grid = new int[4][4];
		for(int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[row].length; column++) {
				grid[row][column] = 1;
			}
		}
		grid[1][1] = 2;
		int [] currentRowAndCol = new int[] {1,1};
		boolean expected = false;
		
		boolean result = gridSolver.pathBranches(grid, currentRowAndCol);
		
		assertEquals(expected, result);
	}

	@Test
	public void testCreateBranch() {
		GridSolver gridSolver = new GridSolver();
		int [] currentRowAndCol = new int[] {1,1};
		ArrayList<int[]> expected = new ArrayList<>();
		expected.add(currentRowAndCol);
		
		gridSolver.addBranches(currentRowAndCol);;
		
		assertArrayEquals(expected.get(0), gridSolver.branches.get(0));
	}
	@Test
	public void testCreateMultipleBranches() {
		GridSolver gridSolver = new GridSolver();
		int [] currentRowAndCol = new int[] {1,1};
		int [] add1 = new int[] {0,1};
		ArrayList<int[]> expected = new ArrayList<>();
		expected.add(currentRowAndCol);
		expected.add(add1);
		
		gridSolver.addBranches(currentRowAndCol);
		gridSolver.addBranches(add1);
		
		assertArrayEquals(expected.get(0), gridSolver.branches.get(0));
	}

	@Test
	public void testResetToPreviousBranch() {
		GridSolver gridSolver = new GridSolver();
		int [] add1 = new int[] {1,1};
		gridSolver.addBranches(add1);
		int[] expected = new int[] {1,1};
		
		int[] result = gridSolver.resetToPreviousBranch(gridSolver.branches);
		
		assertArrayEquals(expected, result);
	}
	@Test
	public void testResetToPreviousOfPrevious() {
		GridSolver gridSolver = new GridSolver();
		int [] add1 = new int[] {1,1};
		int [] add2 = new int[] {0,1};
		int [] add3 = new int[] {0,0};
		gridSolver.addBranches(add1);
		gridSolver.addBranches(add2);
		gridSolver.addBranches(add3);
		gridSolver.deadBranches = 2;
		gridSolver.numOfBranches = gridSolver.numOfBranches - gridSolver.deadBranches;
		int[] expected = new int[] {1,1};
		
		int[] result = gridSolver.resetToPreviousBranch(gridSolver.branches);
		
		assertArrayEquals(expected, result);
	}
	@Test
	public void testToPreviousNewBranchToPrevious() {
		GridSolver gridSolver = new GridSolver();
		int [] add1 = new int[] {1,1};
		int [] add2 = new int[] {0,1};
		int [] add3 = new int[] {0,0};
		gridSolver.addBranches(add1);
		gridSolver.addBranches(add2);
		gridSolver.deadBranches = 1;
		gridSolver.numOfBranches = gridSolver.numOfBranches - gridSolver.deadBranches;
		gridSolver.branches.remove(gridSolver.numOfBranches);
		int[] expected = new int[] {0,0};
		
		int[] result = gridSolver.resetToPreviousBranch(gridSolver.branches);
		gridSolver.addBranches(add3);
		result = gridSolver.resetToPreviousBranch(gridSolver.branches);
		
		assertArrayEquals(expected, result);
	}

}

