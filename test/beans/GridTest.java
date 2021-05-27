package beans;

import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {
	@Test
	public void isJUnitWorkingTest() {
		assertEquals(true, true);
	}
	@Test
	public void createGridCreatesArray() {
		Grid grid = new Grid();
		grid.setGridWidth(10);
		grid.setGridHeight(10);
		int[][] expected = new int[10][10];
		
		int[][] result = grid.createGrid();
		
		assertArrayEquals(expected, result);
		
	}
	@Test
	public void createGridCreatesArrayGreaterHieght() {
		Grid grid = new Grid();
		grid.setGridWidth(7);
		grid.setGridHeight(10);
		int[][] expected = new int[7][10];
		
		int[][] result = grid.createGrid();
		
		assertArrayEquals(expected, result);
	}
	@Test
	public void changeGrid() {
		Grid grid = new Grid();
		grid.setGridWidth(10);
		grid.setGridHeight(10);
		int[][] newGrid = grid.createGrid();
		int[][] expected = new int[10][10];
		expected[0][1] = 1;
		
		int[][] result = grid.changeGrid(0, 1, newGrid, 1);
		
		assertArrayEquals(expected, result);
	}
}
