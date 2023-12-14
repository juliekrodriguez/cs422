package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.OperatorCountCheck;
import TestEngine.TestEngine;

public class NumOfOperators {

	/*
	 * blackbox testing our files we wrote I dont really know why I would do assert
	 * equal
	 */

	@Test
	void LotsofOperatorstest() throws IOException, CheckstyleException {
		OperatorCountCheck check = new OperatorCountCheck();
		String fp = "src/BlackBoxTestSourceCode/LotsOperatorsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(101, check.operatorCount);
	}

	@Test
	void SomeOperatorstest() throws IOException, CheckstyleException {
		OperatorCountCheck check = new OperatorCountCheck();
		String fp = "src/BlackBoxTestSourceCode/SomeOperatorsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(14, check.operatorCount);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		OperatorCountCheck check = new OperatorCountCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(5, check.operatorCount);
	}
}
