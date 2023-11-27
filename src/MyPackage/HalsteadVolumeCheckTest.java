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

public class HalsteadVolumeCheckTest {

	private HalsteadVolumeCheck halsteadVolumeCheck;

	@Before
	public void setUp() {
		halsteadVolumeCheck = new HalsteadVolumeCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		halsteadVolumeCheck.beginTree(rootAST);
		assertEquals(0, halsteadVolumeCheck.getHalsteadVolume(), 0);
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = halsteadVolumeCheck.getDefaultTokens();
		assertEquals(1, defaultTokens.length);
		assertEquals(TokenTypes.EXPR, defaultTokens[0]);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = halsteadVolumeCheck.getAcceptableTokens();
		assertEquals(1, acceptableTokens.length);
		assertEquals(TokenTypes.EXPR, acceptableTokens[0]);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = halsteadVolumeCheck.getRequiredTokens();
		assertEquals(1, requiredTokens.length);
		assertEquals(TokenTypes.EXPR, requiredTokens[0]);
	}

	@Test
	public void testVisitToken() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadVolumeCheck.visitToken(mockAst);

		// Assert that the uniqueOperators, uniqueOperands, and programLength counts
		// have been updated
		assertEquals(1, halsteadVolumeCheck.getUniqueOperators().size());
		assertEquals(0, halsteadVolumeCheck.getUniqueOperands().size());
		assertEquals(1, halsteadVolumeCheck.getProgramLength());
	}

	@Test
	public void testFinishTree() {
		DetailAST mockRootAst = mock(DetailAST.class);
		halsteadVolumeCheck.getUniqueOperators().add(TokenTypes.PLUS);
		halsteadVolumeCheck.getUniqueOperands().add(TokenTypes.LITERAL_INT);

		halsteadVolumeCheck.finishTree(mockRootAst);

		// Assert that the vocabulary and halsteadVolume count have been calculated
		// correctly
		assertEquals(2, halsteadVolumeCheck.getVocabulary());
		assertEquals(2.0, halsteadVolumeCheck.getHalsteadVolume(), 0.001); // Adjust delta as needed
	}

	@Test
	public void testCountOperatorsAndOperands() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.PLUS); // Example operator type

		halsteadVolumeCheck.countOperatorsAndOperands(mockAst);

		// Assert that the uniqueOperators and programLength counts have been updated
		assertEquals(1, halsteadVolumeCheck.getUniqueOperators().size());
		assertEquals(0, halsteadVolumeCheck.getUniqueOperands().size());
		assertEquals(1, halsteadVolumeCheck.getProgramLength());
	}

	@Test
	public void testIsOperator() {
		int[] operatorTypes = { TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.ASSIGN };

		// Test with a valid operator type
		assertTrue(halsteadVolumeCheck.isOperator(TokenTypes.PLUS, operatorTypes));

		// Test with an invalid operator type
		assertFalse(halsteadVolumeCheck.isOperator(TokenTypes.LITERAL_INT, operatorTypes));
	}

	@Test
	public void testIsOperand() {
		int[] operandTypes = { TokenTypes.LITERAL_INT, TokenTypes.IDENT };

		// Test with a valid operand type
		assertTrue(halsteadVolumeCheck.isOperand(TokenTypes.LITERAL_INT, operandTypes));

		// Test with an invalid operand type
		assertFalse(halsteadVolumeCheck.isOperand(TokenTypes.PLUS, operandTypes));
	}
}
