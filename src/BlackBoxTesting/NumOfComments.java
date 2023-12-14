package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.NumberOfCommentCheck;
import TestEngine.TestEngine;

public class NumOfComments {
	@Test
	void SingleLineCommentTest() throws Exception, CheckstyleException {
		NumberOfCommentCheck check = new NumberOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/SingleLineCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(1, check.cc);
	}

	@Test
	void SingleBlockCommentTest() throws Exception, CheckstyleException {
		NumberOfCommentCheck check = new NumberOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/BlockCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(1, check.cc);
	}

	@Test
	void MultSingleLineCommentTest() throws Exception, CheckstyleException {
		NumberOfCommentCheck check = new NumberOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/MultipleSingleLineCommentsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(6, check.cc);
	}

	@Test
	void MultBlockCommentTest() throws Exception, CheckstyleException {
		NumberOfCommentCheck check = new NumberOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/MultipleBlockCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(7, check.cc);
	}

	@Test
	void MixingMultipleCommentCheckTest() throws Exception, CheckstyleException {
		NumberOfCommentCheck check = new NumberOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/MixingMultipleCommentCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(8, check.cc);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		NumberOfCommentCheck check = new NumberOfCommentCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.cc);
	}
}
