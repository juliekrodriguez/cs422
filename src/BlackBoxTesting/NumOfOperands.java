package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.OperandCountCheck;
import TestEngine.TestEngine;

public class NumOfOperands {

	@Test
	void LotsofOperandstest() throws IOException, CheckstyleException {
		OperandCountCheck check = new OperandCountCheck();
		String fp = "src/BlackBoxTestSourceCode/LotsOperandsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(78, check.ands);
	}

	@Test
	void SomeOperandstest() throws IOException, CheckstyleException {
		OperandCountCheck check = new OperandCountCheck();
		String fp = "src/BlackBoxTestSourceCode/SomeOperandsCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(13, check.ands);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		OperandCountCheck check = new OperandCountCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(2, check.ands);
	}

}
