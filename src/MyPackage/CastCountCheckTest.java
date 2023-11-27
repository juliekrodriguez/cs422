package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CastCountCheckTest {

	private CastCountCheck castCountCheck;

	@Before
	public void setUp() {
		castCountCheck = new CastCountCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		castCountCheck.beginTree(rootAST);
		assertEquals(0, castCountCheck.getCastCount()); // The count should be reset in beginTree
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = castCountCheck.getDefaultTokens();
		assertEquals(1, defaultTokens.length);
		assertEquals(TokenTypes.TYPECAST, defaultTokens[0]);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = castCountCheck.getAcceptableTokens();
		assertEquals(1, acceptableTokens.length);
		assertEquals(TokenTypes.TYPECAST, acceptableTokens[0]);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = castCountCheck.getRequiredTokens();
		assertEquals(1, requiredTokens.length);
		assertEquals(TokenTypes.TYPECAST, requiredTokens[0]);
	}

	@Test
	public void testSingleCast() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.TYPECAST);
		castCountCheck.visitToken(ast);
		assertEquals(1, castCountCheck.getCastCount());
	}

	@Test
	public void testMultipleCasts() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.TYPECAST);
		castCountCheck.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.TYPECAST);
		castCountCheck.visitToken(ast2);

		assertEquals(2, castCountCheck.getCastCount());
	}

}
