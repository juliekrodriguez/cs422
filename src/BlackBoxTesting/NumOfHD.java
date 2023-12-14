package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.HalsteadDifficultyCheck;
import TestEngine.TestEngine;

public class NumOfHD {
	@Test
	void HalsteadDifficultyCheckTest() throws Exception, CheckstyleException {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		String fp = "src/BlackBoxTestSourceCode/HalsteadDifficultyCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(2.5, check.halsteadDifficulty);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.halsteadDifficulty);
	}

}
