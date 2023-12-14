package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.HalsteadLengthCheck;
import TestEngine.TestEngine;

public class NumOfHL {
	@Test
	void HalsteadLengthCheckTest() throws Exception, CheckstyleException {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		String fp = "src/BlackBoxTestSourceCode/HalsteadLengthCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(21, check.hl);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.hl);
	}
}
