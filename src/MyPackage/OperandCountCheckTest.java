package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperandCountCheckTest {

	private OperandCountCheck occ;

	@Before
	public void setUp() {
		occ = new OperandCountCheck();
	}

	@Test
	public void testGetDefaultTokens() {
		// Test the getDefaultTokens method
		int[] defaultTokens = occ.getDefaultTokens();
		assertEquals(16, defaultTokens.length); // Make sure the array contains the expected number of tokens
	}

	@Test
	public void testGetAcceptableTokens() {
		// Test the getAcceptableTokens method
		int[] acceptableTokens = occ.getAcceptableTokens();
		assertEquals(16, acceptableTokens.length);
	}

	@Test
	public void testGetRequiredTokens() {
		// Test the getRequiredTokens method
		int[] requiredTokens = occ.getRequiredTokens();
		assertEquals(16, requiredTokens.length);
	}

	@Test
	public void testVisitToken() {
		// Test the visitToken method
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(TokenTypes.LITERAL_FOR);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(TokenTypes.LITERAL_WHILE);

		occ.visitToken(ast1);
		occ.visitToken(ast2);

		assertEquals(2, occ.getOperandsCount());
	}

	@Test
	public void testSingleOperand() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_INT);
		occ.visitToken(ast);
		assertEquals(1, occ.getOperandsCount());
	}

	@Test
	public void testMultipleOperands() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_INT);
		occ.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_DOUBLE);
		occ.visitToken(ast2);

		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.IDENT);
		occ.visitToken(ast3);

		assertEquals(3, occ.getOperandsCount());
	}

	@Test
	public void testMixedTokens() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_INT);
		occ.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_DOUBLE);
		occ.visitToken(ast2);

		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_BOOLEAN);
		occ.visitToken(ast3);

		assertEquals(3, occ.getOperandsCount());
	}
}
