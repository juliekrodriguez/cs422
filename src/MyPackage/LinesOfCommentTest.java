package MyPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LinesOfCommentTest {

	private LinesOfCommentCheck commentCheck;

	@BeforeEach
	public void setUp() {
		commentCheck = new LinesOfCommentCheck();
	}

	@Test
	public void testGetDefaultTokens() {
		int[] tokens = commentCheck.getDefaultTokens();
		assertEquals(2, tokens.length);
		assertEquals(TokenTypes.COMMENT_CONTENT, tokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, tokens[1]);
	}

	@Test
	public void testGetAcceptableTokens() {
		int[] tokens = commentCheck.getAcceptableTokens();
		assertEquals(2, tokens.length);
		assertEquals(TokenTypes.COMMENT_CONTENT, tokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, tokens[1]);
	}

	@Test
	public void testGetRequiredTokens() {
		int[] tokens = commentCheck.getRequiredTokens();
		assertEquals(2, tokens.length);
		assertEquals(TokenTypes.COMMENT_CONTENT, tokens[0]);
		assertEquals(TokenTypes.BLOCK_COMMENT_BEGIN, tokens[1]);
	}

	@Test
	public void testIsCommentNodesRequired() {
		assertEquals(true, commentCheck.isCommentNodesRequired());
	}

	@Test
	public void testVisitToken() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
		DetailAST mockEnd = mock(DetailAST.class);
		when(mockAst.findFirstToken(TokenTypes.BLOCK_COMMENT_END)).thenReturn(mockEnd);
		when(mockAst.getLineNo()).thenReturn(1);
		when(mockEnd.getLineNo()).thenReturn(3);

		commentCheck.visitToken(mockAst);

		assertEquals(2, commentCheck.count);
	}

	@Test
	public void testVisitTokenSingleLineComment() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getType()).thenReturn(TokenTypes.COMMENT_CONTENT);
		commentCheck.visitToken(mockAst);

		assertEquals(1, commentCheck.count);
	}

	@Test
	public void testBeginTree() {
		DetailAST mockAst = mock(DetailAST.class);
		commentCheck.beginTree(mockAst);

	}

	@Test
	public void testFinishTree() {
		DetailAST mockAst = mock(DetailAST.class);
		when(mockAst.getLineNo()).thenReturn(1);
	}
}
