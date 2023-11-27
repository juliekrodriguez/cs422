package MyPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadEffortCheckTest {
	private HalsteadEffortCheck HalsteadEffortCheck;

	@Before
	public void setUp() {
		HalsteadEffortCheck = new HalsteadEffortCheck();
	}

	@Test
	public void testBeginTree() {
		DetailAST rootAST = mock(DetailAST.class);
		HalsteadEffortCheck.beginTree(rootAST);
		assertEquals(0, HalsteadEffortCheck.getDiff(), 0.001); // The difficulty should be reset in
																// beginTree
	}

	@Test
	public void testDefaultTokens() {
		int[] defaultTokens = HalsteadEffortCheck.getDefaultTokens();
		assertEquals(1, defaultTokens.length);
		assertEquals(TokenTypes.EXPR, defaultTokens[0]);
	}

	@Test
	public void testAcceptableTokens() {
		int[] acceptableTokens = HalsteadEffortCheck.getAcceptableTokens();
		assertEquals(1, acceptableTokens.length);
		assertEquals(TokenTypes.EXPR, acceptableTokens[0]);
	}

	@Test
	public void testRequiredTokens() {
		int[] requiredTokens = HalsteadEffortCheck.getRequiredTokens();
		assertEquals(1, requiredTokens.length);
		assertEquals(TokenTypes.EXPR, requiredTokens[0]);
	}

	@Test
	public void testHalsteadEffortCalculation() {
		// Create an instance of HalsteadEffortCheck
		HalsteadEffortCheck halsteadEffortCheck = new HalsteadEffortCheck();

		// Mock objects for testing
		MockHalsteadVolumeCheck hvc = new MockHalsteadVolumeCheck();
		MockHalsteadDifficultyCheck hdc = new MockHalsteadDifficultyCheck();

		// Set the mocked objects
		halsteadEffortCheck.hvc = hvc;
		halsteadEffortCheck.hdc = hdc;

		// Set values for testing
		hvc.setHalsteadVolume(10.0);
		hdc.setHalsteadDifficulty(2.0);

		// Call the methods
		double vol = halsteadEffortCheck.getVol();
		double diff = halsteadEffortCheck.getDiff();
		double effort = halsteadEffortCheck.computeDifficulty();

		// Assert the results
		Assert.assertEquals(10.0, vol, 0.001);
		Assert.assertEquals(2.0, diff, 0.001);
		Assert.assertEquals(20.0, effort, 0.001);
	}

	// Mock class for HalsteadVolumeCheck
	private static class MockHalsteadVolumeCheck extends HalsteadVolumeCheck {
		private double halsteadVolume;

		public void setHalsteadVolume(double volume) {
			this.halsteadVolume = volume;
		}

		@Override
		public double getHalsteadVolume() {
			return halsteadVolume;
		}
	}

	// Mock class for HalsteadDifficultyCheck
	private static class MockHalsteadDifficultyCheck extends HalsteadDifficultyCheck {
		private double halsteadDifficulty;

		public void setHalsteadDifficulty(double difficulty) {
			this.halsteadDifficulty = difficulty;
		}

		@Override
		public double getHalsteadDifficulty() {
			return halsteadDifficulty;
		}
	}
}
