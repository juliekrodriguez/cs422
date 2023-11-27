package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionCountCheck extends AbstractCheck {

	protected int expressionCount;

	@Override
	public void beginTree(DetailAST rootAST) {
		expressionCount = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.DIV,
				TokenTypes.MOD, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.BXOR, TokenTypes.LOR, TokenTypes.LAND,
				TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.LT, TokenTypes.GT, TokenTypes.LE, TokenTypes.GE,
				TokenTypes.INC, TokenTypes.DEC, TokenTypes.METHOD_CALL
				// Add more token types as needed for other expressions
		};
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
		if (isExpressionToken(ast.getType())) {
			expressionCount++;
		}
	}

	// expressions assign - ask if these are actually expressions for this check
	private boolean isExpressionToken(int tokenType) {
		return tokenType == TokenTypes.ASSIGN || tokenType == TokenTypes.PLUS || tokenType == TokenTypes.MINUS
				|| tokenType == TokenTypes.STAR || tokenType == TokenTypes.DIV || tokenType == TokenTypes.MOD
				|| tokenType == TokenTypes.BAND || tokenType == TokenTypes.BOR || tokenType == TokenTypes.BXOR
				|| tokenType == TokenTypes.LOR || tokenType == TokenTypes.LAND || tokenType == TokenTypes.EQUAL
				|| tokenType == TokenTypes.NOT_EQUAL || tokenType == TokenTypes.LT || tokenType == TokenTypes.GT
				|| tokenType == TokenTypes.LE || tokenType == TokenTypes.GE || tokenType == TokenTypes.INC
				|| tokenType == TokenTypes.DEC || tokenType == TokenTypes.METHOD_CALL;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// log(rootAST.getLineNo(), "Number of expressions: " + expressionCount);
	}

	public int getExpressionCount() {
		return expressionCount;
	}
}
