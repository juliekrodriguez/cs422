package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingStatementCountCheck extends AbstractCheck {
	public int loopingStatementCount;

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO };
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		loopingStatementCount = 0;
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
	public void visitToken(DetailAST aAST) {
		int tokenType = aAST.getType();
		switch (tokenType) {
		case TokenTypes.LITERAL_FOR:
		case TokenTypes.LITERAL_WHILE:
		case TokenTypes.LITERAL_DO:
			// This is an operator
			loopingStatementCount++;
			break;
		}
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// log(rootAST.getLineNo(), "Number of looping statements: " +
		// loopingStatementCount);
	}

	public int getLoopingStatementCount() {
		// TODO Auto-generated method stub
		return loopingStatementCount;
	}
}
