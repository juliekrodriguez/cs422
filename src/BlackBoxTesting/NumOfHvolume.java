package BlackBoxTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import MyPackage.HalsteadVolumeCheck;
import TestEngine.TestEngine;

public class NumOfHvolume {

	@Test
	void HalsteadVolumeCheckTest() throws Exception, CheckstyleException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		String fp = "src/BlackBoxTestSourceCode/HalsteadVolumeCheck.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(11.094737505048094, check.halsteadVolume);
	}

	@Test
	void EmptyClasstest() throws IOException, CheckstyleException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		String fp = "src/BlackBoxTestSourceCode/EmptyClass.java";
		TestEngine testEngine = new TestEngine(fp, check);
		testEngine.analyze();
		assertEquals(0.0, check.getHalsteadVolume());
	}

}
