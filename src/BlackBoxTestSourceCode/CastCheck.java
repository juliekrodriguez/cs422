package BlackBoxTestSourceCode;

public class CastCheck {

	public void demonstrateTypeCasting() {
		double doubleValue = 10.5;
		int intValue;

		// Explicit type casting from double to int
		intValue = (int) doubleValue; // This line contains TokenTypes.TYPECAST

		System.out.println("Original double value: " + doubleValue);
		System.out.println("Casted int value: " + intValue);

		// Another type casting example: float to byte
		float floatValue = 123.45f;
		byte byteValue = (byte) floatValue; // This line also contains TokenTypes.TYPECAST

		System.out.println("Original float value: " + floatValue);
		System.out.println("Casted byte value: " + byteValue);
	}

	public static void main(String[] args) {
		CastCheck example = new CastCheck();
		example.demonstrateTypeCasting();
	}
}
