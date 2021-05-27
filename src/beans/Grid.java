package beans;

public class Grid {
	private int gridWidth;
	private int gridHeight;
	
	public int getGridWidth() {
		return gridWidth;
	}
	public void setGridWidth(int width) {
		this.gridWidth = width;
	}
	public int getGridHeight() {
		return gridHeight;
	}
	public void setGridHeight(int height) {
		this.gridHeight = height;
	}
	
	public int[][] createGrid() {
		int[][] gridForMaze = new int[gridWidth][gridHeight];
		return gridForMaze;
	}
	
	public int[][] changeGrid(int width, int height, int[][] gridForMaze, int valueOfChange) {
		int[][] changedGrid = gridForMaze;
		changedGrid[width][height] = valueOfChange;
		return changedGrid;
	}
}
