package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.LoopingStatementCountCheck;
import TestEngine.TestEngine;

public class NumOfLoops {
	@Test
	void LotsOfLoopsTest() throws Exception, CheckstyleException {
		LoopingStatementCountCheck check = new LoopingStatementCountCheck();
		String fp = "src/BlackBoxTestSourceCode/LotsOfLoopsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(10, check.loopingStatementCount);
	}

	@Test
	void SingleNestedLoopTest() throws Exception, CheckstyleException {
		LoopingStatementCountCheck check = new LoopingStatementCountCheck();
		String fp = "src/BlackBoxTestSourceCode/SingleNestedLoopCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(2, check.loopingStatementCount);
	}

	@Test
	void SingleLoopTest() throws Exception, CheckstyleException {
		LoopingStatementCountCheck check = new LoopingStatementCountCheck();
		String fp = "src/BlackBoxTestSourceCode/SingleLoopCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(1, check.loopingStatementCount);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		LoopingStatementCountCheck check = new LoopingStatementCountCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.loopingStatementCount);
	}

}
