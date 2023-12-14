package BlackBoxTestSourceCode;

public class LotsOperandsCheck {

	public void testOperands() {
		// Integer literals
		int num1 = 10;
		int num2 = 20;

		// Floating-point literals
		double price = 99.99;
		float temperature = 35.5f;

		// Character literal
		char grade = 'A';

		// String literal
		String greeting = "Hello, World!";

		// Boolean values
		boolean isAvailable = true;
		boolean isComplete = false;

		// Arithmetic operations with literals and variables
		int sum = num1 + num2; // Using integer variables
		double totalCost = price * 1.08; // Using double variable and literal for tax calculation
		float nextTemp = temperature + 1.0f; // Using float variable and literal

		// String concatenation
		String message = greeting + " Your grade is " + grade;

		// Print results
		System.out.println("Sum: " + sum);
		System.out.println("Total Cost: " + totalCost);
		System.out.println("Next Temperature: " + nextTemp);
		System.out.println(message);
		System.out.println("Availability: " + isAvailable + ", Completion: " + isComplete);
	}

	public static void main(String[] args) {
		LotsOperandsCheck check = new LotsOperandsCheck();
		check.testOperands();
	}
}
