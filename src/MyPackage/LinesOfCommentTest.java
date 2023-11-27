package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LinesOfCommentTest {

	private LinesOfComment linesOfComment;

	@Before
	public void setUp() {
		linesOfComment = new LinesOfComment();
	}

	@Test
	public void testBeginTree() {
		// Test the beginTree method
		DetailAST rootAST = mock(DetailAST.class);
		linesOfComment.beginTree(rootAST);
		assertEquals(0, linesOfComment.getLinesOfComment());
	}

	@Test
	public void testGetDefaultTokens() {
		// Test the getDefaultTokens method
		int[] defaultTokens = linesOfComment.getDefaultTokens();
		assertEquals(2, defaultTokens.length);
		assertEquals(TokenTypes.SINGLE_LINE_COMMENT, defaultTokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, defaultTokens[1]);
	}

	@Test
	public void testGetAcceptableTokens() {
		// Test the getAcceptableTokens method
		int[] acceptableTokens = linesOfComment.getAcceptableTokens();
		assertEquals(2, acceptableTokens.length);
		assertEquals(TokenTypes.SINGLE_LINE_COMMENT, acceptableTokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, acceptableTokens[1]);
	}

	@Test
	public void testGetRequiredTokens() {
		// Test the getRequiredTokens method
		int[] requiredTokens = linesOfComment.getRequiredTokens();
		assertEquals(2, requiredTokens.length);
		assertEquals(TokenTypes.SINGLE_LINE_COMMENT, requiredTokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, requiredTokens[1]);
	}

	@Test
	public void testSingleLineComment() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.SINGLE_LINE_COMMENT);
		linesOfComment.visitToken(ast);
		assertEquals(1, linesOfComment.getLinesOfComment());
	}

	@Test
	public void testBlockComment() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.BLOCK_COMMENT_BEGIN);

		DetailAST endAst = mock(DetailAST.class);
		when(endAst.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.BLOCK_COMMENT_END);
		when(ast.findFirstToken(com.puppycrawl.tools.checkstyle.api.TokenTypes.BLOCK_COMMENT_END)).thenReturn(endAst);

		linesOfComment.visitToken(ast);
		assertEquals(1, linesOfComment.getLinesOfComment());
	}

	@Test
	public void testMixedComments() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.SINGLE_LINE_COMMENT);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.BLOCK_COMMENT_BEGIN);

		DetailAST endAst = mock(DetailAST.class);
		when(endAst.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.BLOCK_COMMENT_END);
		when(ast2.findFirstToken(com.puppycrawl.tools.checkstyle.api.TokenTypes.BLOCK_COMMENT_END)).thenReturn(endAst);

		linesOfComment.visitToken(ast1);
		linesOfComment.visitToken(ast2);

		assertEquals(2, linesOfComment.getLinesOfComment());
	}
}
