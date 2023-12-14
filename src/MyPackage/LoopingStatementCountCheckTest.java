package MyPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingStatementCountCheckTest {

	private LoopingStatementCountCheck loopingStatementCountCheck;

	@BeforeEach
	public void setUp() {
		loopingStatementCountCheck = new LoopingStatementCountCheck();
	}

	@Test
	public void testGetDefaultTokens() {
		// Test the getDefaultTokens method
		int[] defaultTokens = loopingStatementCountCheck.getDefaultTokens();
		assertEquals(3, defaultTokens.length);
		assertEquals(TokenTypes.LITERAL_FOR, defaultTokens[0]);
		assertEquals(TokenTypes.LITERAL_WHILE, defaultTokens[1]);
		assertEquals(TokenTypes.LITERAL_DO, defaultTokens[2]);
	}

	@Test
	public void testBeginTree() {
		// Test the beginTree method
		DetailAST rootAST = mock(DetailAST.class);
		loopingStatementCountCheck.beginTree(rootAST);
		assertEquals(0, loopingStatementCountCheck.getLoopingStatementCount());
	}

	@Test
	public void testGetAcceptableTokens() {
		// Test the getAcceptableTokens method
		int[] acceptableTokens = loopingStatementCountCheck.getAcceptableTokens();
		assertEquals(3, acceptableTokens.length);
		assertEquals(TokenTypes.LITERAL_FOR, acceptableTokens[0]);
		assertEquals(TokenTypes.LITERAL_WHILE, acceptableTokens[1]);
		assertEquals(TokenTypes.LITERAL_DO, acceptableTokens[2]);
	}

	@Test
	public void testGetRequiredTokens() {
		// Test the getRequiredTokens method
		int[] requiredTokens = loopingStatementCountCheck.getRequiredTokens();
		assertEquals(3, requiredTokens.length);
		assertEquals(TokenTypes.LITERAL_FOR, requiredTokens[0]);
		assertEquals(TokenTypes.LITERAL_WHILE, requiredTokens[1]);
		assertEquals(TokenTypes.LITERAL_DO, requiredTokens[2]);
	}

	@Test
	public void testVisitToken() {
		// Test the visitToken method
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(TokenTypes.LITERAL_FOR);
		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(TokenTypes.LITERAL_WHILE);
		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(TokenTypes.LITERAL_DO);

		loopingStatementCountCheck.visitToken(ast1);
		loopingStatementCountCheck.visitToken(ast2);
		loopingStatementCountCheck.visitToken(ast3);

		assertEquals(3, loopingStatementCountCheck.getLoopingStatementCount());
	}

	@Test
	public void testLoopingStatementCount() {
		DetailAST forAst = mock(DetailAST.class);
		when(forAst.getType()).thenReturn(TokenTypes.LITERAL_FOR);

		DetailAST whileAst = mock(DetailAST.class);
		when(whileAst.getType()).thenReturn(TokenTypes.LITERAL_WHILE);

		DetailAST doAst = mock(DetailAST.class);
		when(doAst.getType()).thenReturn(TokenTypes.LITERAL_DO);

		// Simulate the visitToken calls
		loopingStatementCountCheck.visitToken(forAst);
		loopingStatementCountCheck.visitToken(whileAst);
		loopingStatementCountCheck.visitToken(doAst);

		// Simulate the finishTree call
		loopingStatementCountCheck.finishTree(mock(DetailAST.class));

		assertEquals(3, loopingStatementCountCheck.getLoopingStatementCount());
	}

	@Test
	public void testNoLoopingStatement() {
		// Simulate the finishTree call without visiting any looping statements
		loopingStatementCountCheck.finishTree(mock(DetailAST.class));

		assertEquals(0, loopingStatementCountCheck.getLoopingStatementCount());
	}
}
