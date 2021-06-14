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
		int[][] expected = new int[10][7];
		
		int[][] result = grid.createGrid();
		
		assertArrayEquals(expected, result);
	}
}
