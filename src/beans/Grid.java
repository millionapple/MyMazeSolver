package beans;

public class Grid {
	private int gridWidth;
	private int gridHeight;
	
	public void setGridWidth(int width) {
		this.gridWidth = width;
	}
	public void setGridHeight(int height) {
		this.gridHeight = height;
	}
	
	public int[][] createGrid() {
		return new int[gridHeight][gridWidth];
	}
}
