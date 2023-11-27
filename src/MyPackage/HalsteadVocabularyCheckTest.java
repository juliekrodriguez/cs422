package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVocabularyCheckTest {

	private HalsteadVocabularyCheck halsteadVocabularyCheck;

	@Before
	public void setUp() {
		halsteadVocabularyCheck = new HalsteadVocabularyCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		halsteadVocabularyCheck.beginTree(rootAST);
		assertEquals(0, halsteadVocabularyCheck.getHalsteadVocabulary(), 0);
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = halsteadVocabularyCheck.getDefaultTokens();
		assertEquals(1, defaultTokens.length);
		assertEquals(TokenTypes.EXPR, defaultTokens[0]);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = halsteadVocabularyCheck.getAcceptableTokens();
		assertEquals(1, acceptableTokens.length);
		assertEquals(TokenTypes.EXPR, acceptableTokens[0]);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = halsteadVocabularyCheck.getRequiredTokens();
		assertEquals(1, requiredTokens.length);
		assertEquals(TokenTypes.EXPR, requiredTokens[0]);
	}

	@Test
	public void testVisitToken() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadVocabularyCheck.visitToken(mockAst);

		// Assert that the uniqueOperators and uniqueOperands counts have been updated
		assertEquals(1, halsteadVocabularyCheck.getUniqueOperators().size());
		assertEquals(0, halsteadVocabularyCheck.getUniqueOperands().size());
	}

	@Test
	public void testFinishTree() {
		DetailAST mockRootAst = mock(DetailAST.class);
		halsteadVocabularyCheck.getUniqueOperators().add(TokenTypes.PLUS);
		halsteadVocabularyCheck.getUniqueOperands().add(TokenTypes.LITERAL_INT);

		halsteadVocabularyCheck.finishTree(mockRootAst);

		// Assert that the halsteadVocabulary count has been calculated correctly
		assertEquals(2, halsteadVocabularyCheck.getHalsteadVocabulary());
	}

	@Test
	public void testCountOperatorsAndOperands() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadVocabularyCheck.countOperatorsAndOperands(mockAst);

		// Assert that the uniqueOperators count has been updated
		assertEquals(1, halsteadVocabularyCheck.getUniqueOperators().size());
		assertEquals(0, halsteadVocabularyCheck.getUniqueOperands().size());
	}

	@Test
	public void testIsOperator() {
		int[] operatorTypes = { TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.ASSIGN };

		// Test with a valid operator type
		assertTrue(halsteadVocabularyCheck.isOperator(TokenTypes.PLUS, operatorTypes));

		// Test with an invalid operator type
		assertFalse(halsteadVocabularyCheck.isOperator(TokenTypes.LITERAL_INT, operatorTypes));
	}

	@Test
	public void testIsOperand() {
		int[] operandTypes = { TokenTypes.LITERAL_INT, TokenTypes.IDENT };

		// Test with a valid operand type
		assertTrue(halsteadVocabularyCheck.isOperand(TokenTypes.LITERAL_INT, operandTypes));

		// Test with an invalid operand type
		assertFalse(halsteadVocabularyCheck.isOperand(TokenTypes.PLUS, operandTypes));
	}
}
