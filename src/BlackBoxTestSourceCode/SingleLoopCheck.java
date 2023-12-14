package BlackBoxTestSourceCode;

public class SingleLoopCheck {

	public void printArrayElements(int[] array) {
		// Loop through each element in the array
		for (int i = 0; i < array.length; i++) {
			System.out.println("Element at index " + i + ": " + array[i]);
		}
	}

	public static void main(String[] args) {
		SingleLoopCheck checker = new SingleLoopCheck();
		int[] sampleArray = { 1, 2, 3, 4, 5 }; // Example array
		checker.printArrayElements(sampleArray); // Print elements of the array
	}
}
