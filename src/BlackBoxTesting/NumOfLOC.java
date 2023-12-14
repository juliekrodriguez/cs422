package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.LinesOfCommentCheck;
import TestEngine.TestEngine;

public class NumOfLOC {
	@Test
	void SingleLineCommentTest() throws Exception, CheckstyleException {
		LinesOfCommentCheck check = new LinesOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/SingleLineCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(1, check.count);
	}

	@Test
	void SingleBlockCommentTest() throws Exception, CheckstyleException {
		LinesOfCommentCheck check = new LinesOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/BlockCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(3, check.count);
	}

	@Test
	void MultSingleLineCommentTest() throws Exception, CheckstyleException {
		LinesOfCommentCheck check = new LinesOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/MultipleSingleLineCommentsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(6, check.count);
	}

	@Test
	void MultipleBlockCommentCheckTest() throws Exception, CheckstyleException {
		LinesOfCommentCheck check = new LinesOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/MultipleBlockCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(22, check.count);
	}

	@Test
	void MixingMultipleCommentCheckTest() throws Exception, CheckstyleException {
		LinesOfCommentCheck check = new LinesOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/MixingMultipleCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(16, check.count);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		LinesOfCommentCheck check = new LinesOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.count);
	}

}
