package MyPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadLengthCheckTest {

	private HalsteadLengthCheck halsteadLengthCheck;

	@BeforeEach
	public void setUp() {
		halsteadLengthCheck = new HalsteadLengthCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		halsteadLengthCheck.beginTree(rootAST);
		assertEquals(0, halsteadLengthCheck.getlen(), 0);
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = halsteadLengthCheck.getDefaultTokens();
		assertEquals(1, defaultTokens.length);
		assertEquals(TokenTypes.EXPR, defaultTokens[0]);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = halsteadLengthCheck.getAcceptableTokens();
		assertEquals(1, acceptableTokens.length);
		assertEquals(TokenTypes.EXPR, acceptableTokens[0]);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = halsteadLengthCheck.getRequiredTokens();
		assertEquals(1, requiredTokens.length);
		assertEquals(TokenTypes.EXPR, requiredTokens[0]);
	}

	@Test
	public void testVisitToken() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadLengthCheck.visitToken(mockAst);

		// Assert that the totalOperators count has been updated
		assertEquals(1, halsteadLengthCheck.totalOperators);
	}

	@Test
	public void testOperatorTokenHandling() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS);

		halsteadLengthCheck.visitToken(mockAst);

		// Assert that the totalOperators count has been updated
		assertEquals(1, halsteadLengthCheck.totalOperators);
	}

	@Test
	public void testFinishTree() {
		DetailAST mockRootAst = mock(DetailAST.class);
		halsteadLengthCheck.totalOperators = 5;
		halsteadLengthCheck.totalOperands = 3;

		halsteadLengthCheck.finishTree(mockRootAst);

		assertEquals(8, halsteadLengthCheck.hl);
	}

	@Test
	public void testCountOperatorsAndOperands() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadLengthCheck.countOperatorsAndOperands(mockAst);

		// Assert that the totalOperators count has been updated
		assertEquals(1, halsteadLengthCheck.totalOperators);
	}

	@Test
	public void testIsOperator() {
		int[] operatorTypes = { TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.ASSIGN };

		// Test with a valid operator type
		assertTrue(halsteadLengthCheck.isOperator(TokenTypes.PLUS, operatorTypes));

		// Test with an invalid operator type
		assertFalse(halsteadLengthCheck.isOperator(TokenTypes.LITERAL_INT, operatorTypes));
	}

	@Test
	public void testIsOperand() {
		int[] operandTypes = { TokenTypes.LITERAL_INT, TokenTypes.IDENT };

		// Test with a valid operand type
		assertTrue(halsteadLengthCheck.isOperand(TokenTypes.LITERAL_INT, operandTypes));

		// Test with an invalid operand type
		assertFalse(halsteadLengthCheck.isOperand(TokenTypes.PLUS, operandTypes));
	}
}
