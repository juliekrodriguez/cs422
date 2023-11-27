package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class VariableDeclarationCheckTest {

	private VariableDeclarationCheck vdc;

	@Before
	public void setUp() {
		vdc = new VariableDeclarationCheck();
	}

	@Test
	public void testSingleVariableDeclaration() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.VARIABLE_DEF);
		vdc.visitToken(ast);
		assertEquals(1, vdc.getNumVarDec());
	}

	@Test
	public void testMultipleVariableDeclarations() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.VARIABLE_DEF);
		vdc.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.VARIABLE_DEF);
		vdc.visitToken(ast2);

		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.VARIABLE_DEF);
		vdc.visitToken(ast3);

		assertEquals(3, vdc.getNumVarDec());
	}

	@Test
	public void testNoVariableDeclarations() {
		DetailAST ast = mock(DetailAST.class);
		when(ast.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LCURLY);
		vdc.visitToken(ast);
		assertEquals(0, vdc.getNumVarDec());
	}

	@Test
	public void testMixedTokens() {
		DetailAST ast1 = mock(DetailAST.class);
		when(ast1.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.VARIABLE_DEF);
		vdc.visitToken(ast1);

		DetailAST ast2 = mock(DetailAST.class);
		when(ast2.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.LITERAL_RETURN);
		vdc.visitToken(ast2);

		DetailAST ast3 = mock(DetailAST.class);
		when(ast3.getType()).thenReturn(com.puppycrawl.tools.checkstyle.api.TokenTypes.VARIABLE_DEF);
		vdc.visitToken(ast3);

		assertEquals(2, vdc.getNumVarDec());
	}
}
