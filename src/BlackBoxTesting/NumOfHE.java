package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.HalsteadEffortCheck;
import TestEngine.TestEngine;

public class NumOfHE {
	@Test
	void HalsteadEffortChecktest() throws IOException, CheckstyleException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		String fp = "src/BlackBoxTestSourceCode/HalsteadEffortCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(680, check.count);
	}

	@Test
	void HalsteadEffortChecktestOnCast() throws IOException, CheckstyleException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		String fp = "src/BlackBoxTestSourceCode/CastCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(5555, check.count);
	}

	@Test
	void HalsteadEffortChecktestOnEmpty() throws IOException, CheckstyleException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(38, check.count);
	}

}
