package MyPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfCommentCheckTest {

	private NumberOfCommentCheck NumberOfCommentCheck;

	@BeforeEach
	public void setUp() {
		NumberOfCommentCheck = new NumberOfCommentCheck();
	}

	@Test
	public void testBeginTree() {
		// Test the beginTree method
		DetailAST rootAST = mock(DetailAST.class);
		NumberOfCommentCheck.beginTree(rootAST);
		assertEquals(0, NumberOfCommentCheck.getCommentCount());
	}

	@Test
	public void testGetDefaultTokens() {
		// Test the getDefaultTokens method
		int[] defaultTokens = NumberOfCommentCheck.getDefaultTokens();
		assertEquals(2, defaultTokens.length);
	}

	@Test
	public void testGetAcceptableTokens() {
		// Test the getAcceptableTokens method
		int[] acceptableTokens = NumberOfCommentCheck.getAcceptableTokens();
		assertEquals(2, acceptableTokens.length);
		assertEquals(TokenTypes.SINGLE_LINE_COMMENT, acceptableTokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, acceptableTokens[1]);
	}

	@Test
	public void testGetRequiredTokens() {
		// Test the getRequiredTokens method
		int[] requiredTokens = NumberOfCommentCheck.getRequiredTokens();
		assertEquals(2, requiredTokens.length);
		assertEquals(TokenTypes.SINGLE_LINE_COMMENT, requiredTokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, requiredTokens[1]);
	}

	@Test
	public void testVisitToken() {
		// Test the visitToken method
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);

		NumberOfCommentCheck.visitToken(ast1);
		NumberOfCommentCheck.visitToken(ast2);

		assertEquals(2, NumberOfCommentCheck.getCommentCount());
	}

	@Test
	public void testIsCommentNodesRequired() {
		// Test the isCommentNodesRequired method
		assertTrue(NumberOfCommentCheck.isCommentNodesRequired());
	}

	@Test
	public void testSingleLineComment() {
		NumberOfCommentCheck numberOfCommentCheck = new NumberOfCommentCheck();
		DetailAST singleLineComment = createCommentToken(TokenTypes.SINGLE_LINE_COMMENT);
		numberOfCommentCheck.visitToken(singleLineComment);

		assertCommentCount(numberOfCommentCheck, 1);
	}

	@Test
	public void testBlockComment() {
		NumberOfCommentCheck numberOfCommentCheck = new NumberOfCommentCheck();
		DetailAST blockComment = createCommentToken(TokenTypes.BLOCK_COMMENT_BEGIN);
		numberOfCommentCheck.visitToken(blockComment);

		assertCommentCount(numberOfCommentCheck, 1);
	}

	@Test
	public void testNonCommentToken() {
		NumberOfCommentCheck numberOfCommentCheck = new NumberOfCommentCheck();
		DetailAST nonCommentToken = createNonCommentToken();
		numberOfCommentCheck.visitToken(nonCommentToken);

		assertCommentCount(numberOfCommentCheck, 0);
	}

	@Test
	public void testTotalNumberOfComments() {
		NumberOfCommentCheck numberOfCommentCheck = new NumberOfCommentCheck();
		numberOfCommentCheck.visitToken(createCommentToken(TokenTypes.SINGLE_LINE_COMMENT));
		numberOfCommentCheck.visitToken(createCommentToken(TokenTypes.BLOCK_COMMENT_BEGIN));
		numberOfCommentCheck.visitToken(createCommentToken(TokenTypes.SINGLE_LINE_COMMENT));

		assertCommentCount(numberOfCommentCheck, 3);
	}

	private DetailAST createCommentToken(int tokenType) {
		return createMockDetailAST(tokenType);
	}

	private DetailAST createNonCommentToken() {
		return createMockDetailAST(TokenTypes.IDENT);
	}

	private void assertCommentCount(NumberOfCommentCheck numberOfCommentCheck, int expectedCount) {
		assertEquals(expectedCount, numberOfCommentCheck.cc);
	}

	private DetailAST createMockDetailAST(int tokenType) {
		DetailAST mockDetailAST = mock(DetailAST.class);
		when(mockDetailAST.getType()).thenReturn(tokenType);
		return mockDetailAST;
	}
}
