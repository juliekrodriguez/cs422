package BlackBoxTestSourceCode;

public class LotsOperatorsCheck {

	public int testOperators(int num) {
		// Arithmetic Operators
		int sum = num + 5;
		int diff = num - 3;
		int product = num * 2;
		int quotient = num / 2;
		int remainder = num % 4;

		// Increment and Decrement
		num++;
		num--;

		// Bitwise Operators
		int bitwiseAnd = num & 1;
		int bitwiseOr = num | 1;
		int bitwiseXor = num ^ 1;
		int bitwiseComplement = ~num;
		int leftShift = num << 2;
		int rightShift = num >> 2;
		int unsignedRightShift = num >>> 2;

		// Conditional (ternary) Operator
		int max = (num > 50) ? 50 : num;

		// Logical Operators
		boolean isEven = (num % 2 == 0);
		boolean isPositive = num > 0;
		boolean isEvenAndPositive = isEven && isPositive;

		// Combine and return a result (for demonstration)
		int result = sum + diff + product + quotient + remainder + bitwiseAnd + bitwiseOr + bitwiseXor
				+ bitwiseComplement + leftShift + rightShift + unsignedRightShift + max + (isEvenAndPositive ? 1 : 0);

		return result;
	}

	public static void main(String[] args) {
		LotsOperatorsCheck check = new LotsOperatorsCheck();
		int result = check.testOperators(10);
		System.out.println("Result: " + result);
	}
}
