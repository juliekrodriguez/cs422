package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//Halstead Effort is the difficulty multiplied by the volume. Effort = DV. 

public class HalsteadEffortCheck extends AbstractCheck {

	protected HalsteadVolumeCheck hvc = new HalsteadVolumeCheck();
	protected HalsteadDifficultyCheck hdc = new HalsteadDifficultyCheck();
	double effort = 0.0;
	double vol = 0.0;
	double diff = 0.0;

	@Override
	public void beginTree(DetailAST rootAST) {
		effort = 0;
		vol = 0;
		diff = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] { TokenTypes.EXPR };
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}

	public double getVol() {
		vol = hvc.getHalsteadVolume();
		return hvc.getHalsteadVolume();
	}

	public double getDiff() {
		diff = hdc.getHalsteadDifficulty();
		return hdc.getHalsteadDifficulty();
	}

	public double computeDifficulty() {
		effort = vol * diff;
		return effort;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// to do - not for tests
	}

}
