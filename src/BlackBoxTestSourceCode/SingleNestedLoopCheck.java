package BlackBoxTestSourceCode;

public class SingleNestedLoopCheck {

	public void printGrid(int rows, int columns) {
		// Outer loop for rows
		for (int i = 0; i < rows; i++) {
			// Inner loop for columns
			for (int j = 0; j < columns; j++) {
				System.out.print("(" + i + "," + j + ") ");
			}
			System.out.println(); // New line at the end of each row
		}
	}

	public static void main(String[] args) {
		SingleNestedLoopCheck checker = new SingleNestedLoopCheck();
		checker.printGrid(3, 4); // Example: Print a 3x4 grid
	}

}
