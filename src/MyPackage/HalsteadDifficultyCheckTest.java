package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadDifficultyCheckTest {

	private HalsteadDifficultyCheck halsteadDifficultyCheck;

	@Before
	public void setUp() {
		halsteadDifficultyCheck = new HalsteadDifficultyCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		halsteadDifficultyCheck.beginTree(rootAST);
		assertEquals(0, halsteadDifficultyCheck.getHalsteadDifficulty(), 0.001); // The difficulty should be reset in
																					// beginTree
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = halsteadDifficultyCheck.getDefaultTokens();
		assertEquals(1, defaultTokens.length);
		assertEquals(TokenTypes.EXPR, defaultTokens[0]);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = halsteadDifficultyCheck.getAcceptableTokens();
		assertEquals(1, acceptableTokens.length);
		assertEquals(TokenTypes.EXPR, acceptableTokens[0]);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = halsteadDifficultyCheck.getRequiredTokens();
		assertEquals(1, requiredTokens.length);
		assertEquals(TokenTypes.EXPR, requiredTokens[0]);
	}

	@Test
	public void testVisitToken() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadDifficultyCheck.visitToken(mockAst);

		// Assert that the uniqueOperators, uniqueOperands, and totalNumberOfOperands
		// counts have been updated
		assertEquals(1, halsteadDifficultyCheck.getUniqueOperators().size());
		assertEquals(0, halsteadDifficultyCheck.getUniqueOperands().size());
		assertEquals(0, halsteadDifficultyCheck.getTotalNumberOfOperands());
	}

	@Test
	public void testFinishTree() {
		// Create mock root AST
		DetailAST mockRootAst = mock(DetailAST.class);
		when(mockRootAst.getType()).thenReturn(TokenTypes.METHOD_DEF);

		// Simulate multiple operands
		DetailAST operand1 = mock(DetailAST.class);
		when(operand1.getType()).thenReturn(TokenTypes.LITERAL_INT);

		DetailAST operand2 = mock(DetailAST.class);
		when(operand2.getType()).thenReturn(TokenTypes.IDENT);

		// Set up the mock AST structure
		when(mockRootAst.getFirstChild()).thenReturn(operand1);
		when(operand1.getNextSibling()).thenReturn(operand2);

		// Call visitToken on the root to process the operands
		halsteadDifficultyCheck.visitToken(mockRootAst);

		// Call finishTree on the root to calculate Halstead Difficulty
		halsteadDifficultyCheck.finishTree(mockRootAst);

		// Assert that the halsteadDifficulty count has been calculated correctly
		assertEquals(0.5, halsteadDifficultyCheck.getHalsteadDifficulty(), 0.001); // Adjust delta as needed
		assertEquals(2, halsteadDifficultyCheck.getTotalNumberOfOperands());
	}

	@Test
	public void testCountOperatorsAndOperands() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadDifficultyCheck.countOperatorsAndOperands(mockAst);

		// Assert that the uniqueOperators count has been updated
		assertEquals(1, halsteadDifficultyCheck.getUniqueOperators().size());
		assertEquals(0, halsteadDifficultyCheck.getUniqueOperands().size());
		assertEquals(0, halsteadDifficultyCheck.getTotalNumberOfOperands());
	}

	@Test
	public void testIsOperator() {
		int[] operatorTypes = { TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.ASSIGN };

		// Test with a valid operator type
		assertTrue(halsteadDifficultyCheck.isOperator(TokenTypes.PLUS, operatorTypes));

		// Test with an invalid operator type
		assertFalse(halsteadDifficultyCheck.isOperator(TokenTypes.LITERAL_INT, operatorTypes));
	}

	@Test
	public void testIsOperand() {
		int[] operandTypes = { TokenTypes.LITERAL_INT, TokenTypes.IDENT };

		// Test with a valid operand type
		assertTrue(halsteadDifficultyCheck.isOperand(TokenTypes.LITERAL_INT, operandTypes));

		// Test with an invalid operand type
		assertFalse(halsteadDifficultyCheck.isOperand(TokenTypes.PLUS, operandTypes));
	}
}
