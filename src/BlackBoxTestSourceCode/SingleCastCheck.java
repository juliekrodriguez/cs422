package BlackBoxTestSourceCode;

public class SingleCastCheck {
	public void demonstrateTypeCasting() {
		double doubleValue = 10.5;
		int intValue;

		// Explicit type casting from double to int
		intValue = (int) doubleValue; // This line contains TokenTypes.TYPECAST

		System.out.println("Original double value: " + doubleValue);
		System.out.println("Casted int value: " + intValue);
	}

	public static void main(String[] args) {
		SingleCastCheck example = new SingleCastCheck();
		example.demonstrateTypeCasting();
	}

}
