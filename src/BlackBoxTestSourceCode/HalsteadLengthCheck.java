package BlackBoxTestSourceCode;

public class HalsteadLengthCheck {

	// Method to add two numbers
	public int add(int a, int b) {
		return a + b;
	}

	// Method to subtract two numbers
	public int subtract(int a, int b) {
		return a - b;
	}

	// Method to multiply two numbers
	public int multiply(int a, int b) {
		return a * b;
	}

	// Method to divide two numbers
	public double divide(int a, int b) {
		if (b == 0) {
			throw new IllegalArgumentException("Divider cannot be zero.");
		}
		return (double) a / b;
	}

}
//Operands: 21
//Operators: 6