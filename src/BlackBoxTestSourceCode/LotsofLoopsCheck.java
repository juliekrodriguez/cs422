package BlackBoxTestSourceCode;

public class LotsofLoopsCheck {

	// Method with a basic for loop
	public void basicForLoop() {
		for (int i = 0; i < 10; i++) {
			System.out.println("For Loop, iteration: " + i);
		}
	}

	// Method with a while loop
	public void basicWhileLoop() {
		int i = 0;
		while (i < 10) {
			System.out.println("While Loop, iteration: " + i);
			i++;
		}
	}

	// Method with a do-while loop
	public void basicDoWhileLoop() {
		int i = 0;
		do {
			System.out.println("Do-While Loop, iteration: " + i);
			i++;
		} while (i < 10);
	}

	// Method with a nested for loop
	public void nestedForLoop() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.println("Nested For Loop, iteration: " + i + ", " + j);
			}
		}
	}

	// Method with a for-each loop
	public void forEachLoop() {
		int[] numbers = { 1, 2, 3, 4, 5 };
		for (int number : numbers) {
			System.out.println("For-Each Loop, number: " + number);
		}
	}

	// Dead Code
	public void deadCode() {
		for (int i = 0; i < 10; i++) {
			if (i < 0) { // This condition will never be true
				System.out.println("This is dead code");
			}
		}
	}

	// Break and Continue Misuse
	public void misuseBreakContinue() {
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				continue; // Intended to break here, but used continue by mistake
			}
			System.out.println("i: " + i);
		}
	}

	// Improper Loop Variable Modification
	public void improperModification() {
		for (int i = 0; i < 10; i++) {
			System.out.println("i: " + i);
			i = i - 1; // Modifying loop variable in an unintended way
		}
	}

	// Off-by-One Error
	public void offByOneError() {
		for (int i = 0; i <= 10; i++) { // Should be i < 10 for 10 iterations
			System.out.println("Iteration: " + i);
		}
	}

	// Main method to demonstrate loop methods
	public static void main(String[] args) {
		LotsofLoopsCheck loops = new LotsofLoopsCheck();

		loops.basicForLoop();
		loops.basicWhileLoop();
		loops.basicDoWhileLoop();
		loops.nestedForLoop();
		loops.forEachLoop();
		loops.deadCode();
		loops.misuseBreakContinue();
		loops.improperModification();
		loops.offByOneError();
	}
}
