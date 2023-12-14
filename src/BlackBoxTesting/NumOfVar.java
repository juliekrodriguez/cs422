package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.VariableDeclarationCheck;
import TestEngine.TestEngine;

public class NumOfVar {
	@Test
	void VariableDeclarationChecktest() throws IOException, CheckstyleException {
		VariableDeclarationCheck check = new VariableDeclarationCheck();
		String fp = "src/BlackBoxTestSourceCode/VariableDeclarationCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(5, check.vc);
	}
}
