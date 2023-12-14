package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CastCountCheck extends AbstractCheck {

	public int castCount = 0;

	@Override
	public void beginTree(DetailAST rootAST) {
		castCount = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.TYPECAST };
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

	@Override
	public void visitToken(DetailAST ast) {
		castCount++;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// log(rootAST.getLineNo(), "Number of casts: " + castCount);
	}

	public int getCastCount() {
		return castCount;
	}
}
