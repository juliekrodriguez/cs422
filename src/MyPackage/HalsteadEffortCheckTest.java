package MyPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HalsteadEffortCheckTest {
	private HalsteadEffortCheck HalsteadEffortCheck;
	private DetailAST mockAst;

	@BeforeEach
	public void setUp() {
		HalsteadEffortCheck = new HalsteadEffortCheck();
		mockAst = mock(DetailAST.class);
	}

	@Test
	public void testBeginTree() {
		HalsteadEffortCheck spy = spy(new HalsteadEffortCheck());
		DetailAST mockAST = mock(DetailAST.class);
		spy.beginTree(mockAST);
		verify(spy, atLeastOnce()).beginTree(mockAST);
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = HalsteadEffortCheck.getDefaultTokens();
		assertEquals(110, defaultTokens.length);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = HalsteadEffortCheck.getAcceptableTokens();
		assertEquals(110, acceptableTokens.length);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = HalsteadEffortCheck.getRequiredTokens();
		assertEquals(110, requiredTokens.length);
	}

	@Test
	void testVisitToken() {
		HalsteadEffortCheck spy = spy(new HalsteadEffortCheck());
		DetailAST mockAST = mock(DetailAST.class);
		spy.visitToken(mockAST);
		verify(spy, atLeastOnce()).visitToken(mockAST);
	}

	@Test
	void finishTreeTest() {
		HalsteadEffortCheck spy = spy(new HalsteadEffortCheck());
		DetailAST mockAST = mock(DetailAST.class);
		String message = "hals effort: 0";
		doNothing().when(spy).log(0, message);
		spy.finishTree(mockAST);
		verify(spy).finishTree(mockAST);
	}

}
