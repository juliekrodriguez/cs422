package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperandCountCheck extends AbstractCheck {
	private int ands = 0;

	@Override
	public void beginTree(DetailAST rootAST) {
		ands = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		// pull from hals tokens instead
		return new int[] { TokenTypes.LITERAL_VOID, TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BYTE,
				TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT,
				TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_DOUBLE, TokenTypes.IDENT, TokenTypes.NUM_DOUBLE,
				TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, TokenTypes.CHAR_LITERAL,
				TokenTypes.STRING_LITERAL };
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

	@Override
	public void visitToken(DetailAST aAST) {
		ands++;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// log(rootAST.getLineNo(), "ands:" + ands);
	}

	public int getOperandsCount() {
		return ands;
	}
}
