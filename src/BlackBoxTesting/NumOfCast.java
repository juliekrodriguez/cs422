package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.CastCountCheck;
import TestEngine.TestEngine;

public class NumOfCast {

	@Test
	void SingleCheckTest() throws Exception, CheckstyleException {
		CastCountCheck check = new CastCountCheck();
		String fp = "src/BlackBoxTestSourceCode/SingleCastCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(1, check.castCount);
	}

	@Test
	void CastCountCheckTest() throws Exception, CheckstyleException {
		CastCountCheck check = new CastCountCheck();
		String fp = "src/BlackBoxTestSourceCode/CastCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(2, check.castCount);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		CastCountCheck check = new CastCountCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.castCount);
	}

}
