package beans;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.Rule;

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
	public void changeGridTest() {
		Grid grid = new Grid();
		grid.setGridWidth(10);
		grid.setGridHeight(10);
		int[][] newGrid = grid.createGrid();
		int[][] expected = new int[10][10];
		expected[0][1] = 1;
		
		int[][] result = grid.changeGrid(0, 1, newGrid, 1);
		
		assertArrayEquals(expected, result);
	}
	@Test
	public void changeGridMultipleValuesTest() {
		Grid grid = new Grid();
		grid.setGridWidth(10);
		grid.setGridHeight(10);
		int[][] newGrid = grid.createGrid();
		int[][] expected = new int[10][10];
		expected[9][1] = 2;
		expected[5][0] = 1;
		expected[1][1] = 3;
		
		newGrid = grid.changeGrid(9, 1, newGrid, 2);
		newGrid = grid.changeGrid(5, 0, newGrid, 1);
		newGrid = grid.changeGrid(1, 1, newGrid, 3);
		
		assertArrayEquals(expected, newGrid);
	}
	
	 @Rule
	 public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void changeGridTakesInHigherThanGridValueTest() {
		exception.expect(ArrayIndexOutOfBoundsException.class);
		Grid grid = new Grid();
		grid.setGridWidth(10);
		grid.setGridHeight(10);
		int[][] newGrid = grid.createGrid();
		
		grid.changeGrid(11, 11, newGrid, 1);
		
	}
}
