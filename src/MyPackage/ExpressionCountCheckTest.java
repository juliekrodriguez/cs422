package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class ExpressionCountCheckTest {

	private ExpressionCountCheck ecc;

	@Before
	public void setUp() {
		ecc = new ExpressionCountCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		ecc.beginTree(rootAST);
		assertEquals(0, ecc.expressionCount); // The count should be reset in beginTree
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = ecc.getDefaultTokens();
		assertEquals(20, defaultTokens.length);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = ecc.getAcceptableTokens();
		assertEquals(20, acceptableTokens.length);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = ecc.getRequiredTokens();
		assertEquals(20, requiredTokens.length);
	}

	@Test
	public void testSingleExpression() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.ASSIGN);
		ecc.visitToken(ast);
		assertEquals(1, ecc.getExpressionCount());
	}

	@Test
	public void testMultipleExpressions() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.ASSIGN);
		ecc.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.METHOD_CALL);
		ecc.visitToken(ast2);

		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.PLUS);
		ecc.visitToken(ast3);

		assertEquals(3, ecc.getExpressionCount());
	}

	@Test
	public void testNoExpressions() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LCURLY);
		ecc.visitToken(ast);
		assertEquals(0, ecc.getExpressionCount());
	}

	@Test
	public void testMixedTokens() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.ASSIGN);
		ecc.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.METHOD_CALL);
		ecc.visitToken(ast2);

		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.NUM_FLOAT);
		ecc.visitToken(ast3);

		assertEquals(2, ecc.getExpressionCount());
	}
}
