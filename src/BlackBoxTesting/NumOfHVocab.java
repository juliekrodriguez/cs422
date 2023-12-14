package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.HalsteadVocabularyCheck;
import TestEngine.TestEngine;

public class NumOfHVocab {

	@Test
	void HalsteadVocabularyCheckTest() throws Exception, CheckstyleException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		String fp = "src/BlackBoxTestSourceCode/HalsteadVocabCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(3, check.halsteadVocabulary);
	}

	@Test
	void SinglesVocabularyCheckTest() throws Exception, CheckstyleException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		String fp = "src/BlackBoxTestSourceCode/SinglesVocabCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(1, check.halsteadVocabulary);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0, check.halsteadVocabulary);
	}

}
