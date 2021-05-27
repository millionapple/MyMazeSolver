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
		int[][] expected = new int[10][10];
		int width = 10;
		int height = 10;
		
		int[][] result = createGrid(width, height);
		
		assertArrayEquals(expected, result);
		
	}
	private int[][] createGrid(int width, int height) {
		int[][] gridForMaze = new int[width][height];
		return gridForMaze;
	}
}
