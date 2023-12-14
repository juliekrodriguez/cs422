package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.ExpressionCountCheck;
import TestEngine.TestEngine;

public class NumOfExpression {
	@Test
	void LotsOfExpressionCheckTest() throws Exception, CheckstyleException {
		ExpressionCountCheck check = new ExpressionCountCheck();
		String fp = "src/BlackBoxTestSourceCode/LotsOfExpressionCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(7, check.count);
	}

	@Test
	void ExpressionCheckTest() throws Exception, CheckstyleException {
		ExpressionCountCheck check = new ExpressionCountCheck();
		String fp = "src/BlackBoxTestSourceCode/ExpressionCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(3, check.count);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		ExpressionCountCheck check = new ExpressionCountCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.count);
	}
}
